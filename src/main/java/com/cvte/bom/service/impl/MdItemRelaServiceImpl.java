package com.cvte.bom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvte.bom.entity.MdItemRela;
import com.cvte.bom.mapper.MdItemRelaMapper;
import com.cvte.bom.service.MdItemRelaService;
import com.cvte.bom.vo.MdItemTreeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jonil
 * @since 2022-12-06
 */
@Service
public class MdItemRelaServiceImpl extends ServiceImpl<MdItemRelaMapper, MdItemRela> implements MdItemRelaService {

    /**
     * 根据父节点id查找子节点
     *
     * @param pid
     * @return
     */
    @Override
    public List<MdItemTreeVO> selectByPid(Integer pid) {
        if (pid == null) {
            return null;
        }
        QueryWrapper<MdItemRela> queryWrapper = new QueryWrapper<MdItemRela>().eq("parent_id", pid);
        //重新封装成VO返回该节点的所有子节点
        List<MdItemTreeVO> children = baseMapper.selectList(queryWrapper).stream().map(item -> {
            MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
            BeanUtils.copyProperties(item, mdItemTreeVO);
            return mdItemTreeVO;
        }).collect(Collectors.toList());
        return children;
    }

    /**
     * 根据子节点id查找父节点
     *
     * @param cid
     * @return
     */
    @Override
    public List<MdItemTreeVO> selectByCid(Integer cid) {
        if (cid == null) {
            return null;
        }
        QueryWrapper<MdItemRela> queryWrapper = new QueryWrapper<MdItemRela>().eq("child_id", cid);
        //重新封装成VO返回该节点的所有子节点
        List<MdItemTreeVO> parents = baseMapper.selectList(queryWrapper).stream().map(item -> {
            MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
            BeanUtils.copyProperties(item, mdItemTreeVO);
            return mdItemTreeVO;
        }).collect(Collectors.toList());
        return parents;
    }

    /**
     * 根据父节点查询当前构件并返回所有的子节点
     *
     * @param pid
     * @return
     */
    @Override
    public List<MdItemTreeVO> selectByPidAndCheck(Integer pid) {
        if (pid == null) {
            return null;
        }
        QueryWrapper<MdItemRela> queryWrapper = new QueryWrapper<MdItemRela>().eq("parent_id", pid).eq("item_check", 1);
        //重新封装成VO返回该节点的所有子节点
        List<MdItemTreeVO> children = baseMapper.selectList(queryWrapper).stream().map(item -> {
            MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
            BeanUtils.copyProperties(item, mdItemTreeVO);
            return mdItemTreeVO;
        }).collect(Collectors.toList());
        return children;
    }

    /**
     * 根据多个子节点查询当前构件并返回所有的父节点
     *
     * @param cids
     * @return
     */
    @Override
    public List<MdItemTreeVO> selectByCidsAndCheck(Integer[] cids) {
        if (cids == null || cids.length == 0) {
            return null;
        }
        QueryWrapper<MdItemRela> queryWrapper = new QueryWrapper<MdItemRela>().in("child_id", Arrays.asList(cids));
        //重新封装成VO返回该节点的所有子节点
        List<MdItemTreeVO> parents = baseMapper.selectList(queryWrapper).stream().map(item -> {
            MdItemTreeVO mdItemTreeVO = new MdItemTreeVO();
            BeanUtils.copyProperties(item, mdItemTreeVO);
            return mdItemTreeVO;
        }).collect(Collectors.toList());
        return parents;
    }
}
