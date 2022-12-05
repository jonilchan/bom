package com.cvte.bom.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: 实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MdItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物料ID（自增）
     */
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    /**
     * 物料编码
     */
    private String itemCode;

    /**
     * 物料名称
     */
    private String itemName;

    /**
     * 物料分类代码
     */
    private String itemClassCode;

    /**
     * 物料分类名称
     */
    private String itemClassName;

    /**
     * 物料父节点id
     */
    private Integer itemParentId;

    /**
     * 物料数量
     */
    private Integer itemQuantity;

    /**
     * 物料状态，0-为当前物料，1-为替代物料
     */
    private Integer itemCheck;

    /**
     * 物料所在层级，从1开始
     */
    private Integer itemLevel;

    /**
     * 创建者
     */
    private String crtUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime crtTime;

    /**
     * 更新者
     */
    private String updUser;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updTime;


}