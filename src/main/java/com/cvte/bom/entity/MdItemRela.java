package com.cvte.bom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jonil
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MdItemRela implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联关系自增id
     */
    @TableId(value = "md_item_rela_id", type = IdType.AUTO)
    private Integer mdItemRelaId;

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
     * 物料状态，1-为当前物料，0-为替代物料， 2-为旧版本物料
     */
    private Integer itemCheck;


}
