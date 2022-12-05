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

    //当前物料
    currentItem(0),
    //替代物料
    alternativeItem(1);
    //状态码
    Integer code;

}
