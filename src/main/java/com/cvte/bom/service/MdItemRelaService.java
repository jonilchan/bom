package com.cvte.bom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cvte.bom.entity.MdItemRela;
import com.cvte.bom.vo.MdItemTreeVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jonil
 * @since 2022-12-06
 */
public interface MdItemRelaService extends IService<MdItemRela> {

    /**
     * 根据父节点id查找子节点
     *
     * @param pid
     * @return
     */
    List<MdItemTreeVO> selectByPid(Integer pid);

    /**
     * 根据子节点id查找父节点
     *
     * @param cid
     * @return
     */
    List<MdItemTreeVO> selectByCid(Integer cid);

    /**
     * 根据父节点查询当前构件并返回所有的子节点
     *
     * @param pid
     * @return
     */
    List<MdItemTreeVO> selectByPidAndCheck(Integer pid);

    /**
     * 根据多个子节点查询当前构件并返回所有的父节点
     *
     * @param cids
     * @return
     */
    List<MdItemTreeVO> selectByCidsAndCheck(Integer[] cids);
}
