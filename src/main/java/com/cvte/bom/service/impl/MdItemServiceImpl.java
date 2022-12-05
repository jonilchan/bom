package com.cvte.bom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvte.bom.entity.MdItem;
import com.cvte.bom.exception.ParamsException;
import com.cvte.bom.mapper.MdItemMapper;
import com.cvte.bom.service.MdItemService;
import com.cvte.bom.vo.MdItemTreeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: 服务实现类
 */
@Service
public class MdItemServiceImpl extends ServiceImpl<MdItemMapper, MdItem> implements MdItemService {


    /**
     * 通过itemId获取MdItem树形数据
     *
     * @param itemId
     * @return
     */
    @Override
    public MdItemTreeVO getMdItemTreeById(Integer itemId, String[] invisible, Integer level) {
        MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
        //查询是否存在该节点
        MdItem mdItem = baseMapper.selectById(itemId);
        if (mdItem == null) {
            throw new ParamsException("不存在该参数节点");
        }
        BeanUtils.copyProperties(mdItem, mdItemTreeVO);
        return getChildrenById(mdItemTreeVO, invisible, level);
    }

    /**
     * 递归获取子节点，拼装成树形数据
     *
     * @param mdItemTreeVO
     * @return
     */
    public MdItemTreeVO getChildrenById(MdItemTreeVO mdItemTreeVO, String[] invisible, Integer level) {
        QueryWrapper<MdItem> queryWrapper = new QueryWrapper<MdItem>().eq("item_parent_id", mdItemTreeVO.getItemId());
        List<MdItem> list = baseMapper.selectList(queryWrapper);
        //节点不为空则进行查询子树
        if (list != null) {
            List<MdItemTreeVO> collect = list.stream()
                    //过滤存在不可见元素和高于可视层级元素
                    .filter(item -> Arrays.stream(invisible).noneMatch(macheElement -> macheElement.equals(item.getItemClassCode())) &&
                            level > 0 && item.getItemLevel() <= level).map(item -> {
                        MdItemTreeVO itemTreeVO = new MdItemTreeVO();
                        BeanUtils.copyProperties(item, itemTreeVO);
                        //下一层递归查询
                        return getChildrenById(itemTreeVO, invisible, level);
                        //收集元素
                    }).collect(Collectors.toList());
            mdItemTreeVO.setMdItemList(collect);
        }
        return mdItemTreeVO;
    }

    /**
     * 通过itemCode获取MdItem的成品路径
     *
     * @param strings
     * @return 例：004.003 --> 002.01 --> 001.01
     */
    @Override
    public String getMdItemTraceById(String[] strings) {

        QueryWrapper<MdItem> queryWrapper = new QueryWrapper<MdItem>().eq("item_class_code", strings[0]).eq("item_code", strings[1]);
        MdItem item = baseMapper.selectOne(queryWrapper);
        if (item == null) {
            throw new ParamsException();
        }

        StringBuilder res = new StringBuilder();
        res.append(strings[0]);
        res.append('.');
        res.append(strings[1]);
        while (true) {
            MdItem mdItem = baseMapper.selectById(item.getItemParentId());
            if (mdItem == null) {
                break;
            }
            res.append(" --> ");
            res.append(mdItem.getItemClassCode());
            res.append('.');
            res.append(mdItem.getItemCode());
            item = mdItem;
        }
        return res.toString();
    }

    /**
     * 通过多个id获取采购清单
     *
     * @param ids
     * @return
     */
    @Override
    public List<MdItem> getMdItemListByIds(Integer[] ids) {
        QueryWrapper<MdItem> queryWrapper = new QueryWrapper<MdItem>().in("item_id", Arrays.asList(ids));
        List<MdItem> mdItems = baseMapper.selectList(queryWrapper);
        List<MdItem> purchaseList = new ArrayList<>();
        getMdItemList(purchaseList, mdItems);
        return purchaseList;
    }

    /**
     * 递归获取孩子节点，并加入采购清单
     *
     * @param purchaseList
     * @param children
     */
    public void getMdItemList(List<MdItem> purchaseList, List<MdItem> children) {
        for (MdItem child : children) {
            if (child == null) {
                continue;
            }
            if (child.getItemClassCode().equals("004")) {
                purchaseList.add(child);
                continue;
            }
            getMdItemList(purchaseList, getMdItemListByParentId(child.getItemId()));
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
