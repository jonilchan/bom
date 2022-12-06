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
     * 父节点id
     */
    private Integer parentId;

    /**
     * 子节点id
     */
    private Integer childId;

    /**
     * 子节点数量
     */
    private Integer childQuality;

    /**
     * 物料状态，0-为当前物料，1-为替代物料
     */
    private Integer itemCheck;

    /**
     * 子节点列表
     */
    private List<MdItemTreeVO> children;
}
