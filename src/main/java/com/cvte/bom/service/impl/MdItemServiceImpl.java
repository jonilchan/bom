package com.cvte.bom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvte.bom.entity.MdItem;
import com.cvte.bom.enums.BomItemCheckEnum;
import com.cvte.bom.exception.ParamsException;
import com.cvte.bom.mapper.MdItemMapper;
import com.cvte.bom.service.MdItemRelaService;
import com.cvte.bom.service.MdItemService;
import com.cvte.bom.vo.MdItemTreeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: 服务实现类
 */
@Service
public class MdItemServiceImpl extends ServiceImpl<MdItemMapper, MdItem> implements MdItemService {

    @Autowired
    private MdItemRelaService mdItemRelaService;

    /**
     * 通过itemId获取MdItem树形数据
     *
     * @param itemId
     * @return
     */
    @Override
    public MdItemTreeVO getMdItemTreeById(Integer itemId, String[] invisible) {
        MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
        //查询是否存在该节点
        MdItem mdItem = baseMapper.selectById(itemId);
        if (mdItem == null) {
            throw new ParamsException("不存在该参数节点");
        }
        BeanUtils.copyProperties(mdItem, mdItemTreeVO);
        MdItemTreeVO res = getChildrenById(mdItemTreeVO);
        if (invisible != null && invisible.length > 0) {
            getMdItemTreeByIdHelper(res, invisible);
        }
        return res;
    }

    /**
     * 递归获取子节点，拼装成树形数据，返回父节点
     *
     * @param mdItemTreeVO
     * @return
     */
    public MdItemTreeVO getChildrenById(MdItemTreeVO mdItemTreeVO) {
        List<MdItemTreeVO> children = mdItemRelaService.selectByPid(mdItemTreeVO.getItemId());
        mdItemTreeVO.setChildren(children);
        //节点不为空则进行查询子树
        for (MdItemTreeVO child : children) {
            MdItem mdItem = baseMapper.selectById(child.getChildId());
            BeanUtils.copyProperties(mdItem, child);
            getChildrenById(child);
        }
        return mdItemTreeVO;
    }

    /**
     * 过滤某些不可见元素
     *
     * @param node
     */
    public void getMdItemTreeByIdHelper(MdItemTreeVO node, String[] invisible) {

        node.getChildren().removeIf(item -> {
            for (String s : invisible) {
                if (item.getItemClassCode().equals(s)) {
                    return true;
                }
            }
            return false;
        });
        //递归去除
        for (MdItemTreeVO child : node.getChildren()) {
            getMdItemTreeByIdHelper(child, invisible);
        }
    }

    public void genTree(MdItemTreeVO node, StringBuilder temp, int level){
        level++;
        if (node == null){
            return;
        }
        for (MdItemTreeVO child : node.getChildren()) {
            if (child.getChildren() != null){
                genTreeHelper(child, temp, level);
                genTree(child, temp, level);
            }else{
                genTreeHelper(child, temp, level);
            }
        }
    }

    /**
     * 目录树层级打印助手
     * @param level
     * @return
     */
    private String levelSign(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ├─");
        for (int x = 0; x < level; x++) {
            sb.insert(0, " │   ");
        }
        return sb.toString();
    }

    /**
     * 生成目录树助手，显示新旧版本和替代物品
     * @param node
     * @param temp
     * @param level
     */
    private void genTreeHelper(MdItemTreeVO node, StringBuilder temp, int level){
        temp.append(levelSign(level)).append("料号：").append(node.getItemClassCode()).append('.').append(node.getItemCode()).append(";名称：").append(node.getItemName());
        if (Objects.equals(node.getItemCheck(), BomItemCheckEnum.alternativeItem.getCode())){
            temp.append("（替代物料）");
        }
        if (Objects.equals(node.getItemCheck(), BomItemCheckEnum.oldVersionItem.getCode())){
            temp.append("（旧版本物料）");
        }
        temp.append("\n");
    }

    /**
     * 通过itemCode获取MdItem的成品路径，包含自身节点
     *
     * @param strings
     * @return 例：004.03 --> 002.01 --> 001.01
     */
    @Override
    public String getMdItemTraceById(String[] strings) {

        QueryWrapper<MdItem> queryWrapper = new QueryWrapper<MdItem>().eq("item_class_code", strings[0]).eq("item_code", strings[1]);
        MdItem item = baseMapper.selectOne(queryWrapper);
        if (item == null) {
            throw new ParamsException();
        }
        List<List<Integer>> res = new ArrayList<>();
        traceHelper(res, new HashSet<>(), new ArrayList<>(), item.getItemId());
        List<String> all = new ArrayList<>();
        for (List<Integer> nums : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer num : nums) {
                if (num == null || num == 0) {
                    continue;
                }
                MdItem info = baseMapper.selectById(num);
                sb.append(info.getItemClassCode()).append('.').append(info.getItemCode()).append(" --> ");
            }
            sb.delete(sb.length() - 5, sb.length());
            all.add(sb.toString());
        }
        return all.toString();
    }

    /**
     * 回溯助手函数，回溯的时候注意去重
     * @param res
     * @param set
     * @param temp
     * @param id
     */
    private void traceHelper(List<List<Integer>> res, HashSet<String> set, List<Integer> temp, Integer id) {
        if (id != null){
            temp.add(id);
        }
        List<MdItemTreeVO> parents = mdItemRelaService.selectByCid(id);
        if (parents == null || parents.size() == 0) {
            for (String s : set) {
                if (s.contains(temp.toString().substring(1, temp.toString().length() - 1))){
                    return;
                }
            }
            res.add(new ArrayList<>(temp));
            set.add(temp.toString().substring(1, temp.toString().length() - 1));
            return;
        }
        for (MdItemTreeVO parent : parents) {
            traceHelper(res, set, new ArrayList<>(temp), parent.getParentId());
        }
    }

    /**
     * 通过多个id获取采购清单
     *
     * @param ids
     * @return
     */
    @Override
    public List<MdItemTreeVO> getMdItemListByIds(Integer[] ids) {

        Map<Integer, Integer> idsAndQuality = new HashMap<>();
        List<MdItemTreeVO> nodes = mdItemRelaService.selectByCidsAndCheck(ids);
        for (MdItemTreeVO node : nodes) {
            getMdItemList(idsAndQuality, node);
        }
        List<MdItemTreeVO> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : idsAndQuality.entrySet()) {
            MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
            MdItem mdItem = baseMapper.selectById(entry.getKey());
            BeanUtils.copyProperties(mdItem, mdItemTreeVO);
            mdItemTreeVO.setChildQuality(entry.getValue());
            res.add(mdItemTreeVO);
        }
        res.removeIf(item -> !"004".equals(item.getItemClassCode()));
        return res;
    }

    /**
     * 递归获取孩子节点，并加入采购清单
     *
     * @param idsAndQuality
     * @param node
     */
    public void getMdItemList(Map<Integer, Integer> idsAndQuality, MdItemTreeVO node) {
        idsAndQuality.put(node.getChildId(), idsAndQuality.getOrDefault(node.getChildId(), 0) + node.getChildQuality());
        List<MdItemTreeVO> info = mdItemRelaService.selectByPidAndCheck(node.getChildId());
        for (MdItemTreeVO mdItemTreeVO : info) {
            getMdItemList(idsAndQuality, mdItemTreeVO);
        }
    }

    /**
     * 通过父节点id查询子节点列表
     *
     * @param parentId
     * @return
     */
    public List<MdItem> getMdItemListByParentId(Integer parentId) {
        QueryWrapper<MdItem> queryWrapper = new QueryWrapper<MdItem>().eq("item_parent_id", parentId);
        return baseMapper.selectList(queryWrapper);
    }
}
