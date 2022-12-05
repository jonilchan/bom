package com.cvte.bom.vo;

import com.cvte.bom.entity.MdItem;
import lombok.Data;

import java.util.List;

/**
 * @Author: Jonil
 * @Date: 2022/12/5
 * @Description: MdItem树形结构VO
 */
@Data
public class MdItemTreeVO extends MdItem {

    /**
     * 子节点List
     */
    List<MdItemTreeVO> mdItemList;
}
