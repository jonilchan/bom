package com.cvte.bom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jonil
 * @Date: 2022/12/5
 * @Description: 当前物料与替代无聊的枚举类
 */
@Getter
@AllArgsConstructor
public enum BomItemCheckEnum {

    //替代物料
    alternativeItem(0),
    //当前物料
    currentItem(1),
    //旧版本物料
    oldVersionItem(2);
    //状态码
    Integer code;

}
