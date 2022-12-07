package com.cvte.bom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cvte.bom.entity.MdItem;
import com.cvte.bom.vo.MdItemTreeVO;

import java.util.List;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: 服务类
 */
public interface MdItemService extends IService<MdItem> {

    /**
     * 通过itemId获取MdItem树形数据
     *
     * @param itemId
     * @return
     */
    MdItemTreeVO getMdItemTreeById(Integer itemId, String[] invisible);

    void genTree(MdItemTreeVO node, StringBuilder temp, int level);

    /**
     * 过itemCode获取MdItem的成品路径
     *
     * @param strings
     * @return
     */
    String getMdItemTraceById(String[] strings);

    /**
     * 通过多个id获取采购清单
     *
     * @param ids
     * @return
     */
    List<MdItemTreeVO> getMdItemListByIds(Integer[] ids);
}
