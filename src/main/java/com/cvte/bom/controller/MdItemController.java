package com.cvte.bom.controller;

import com.cvte.bom.exception.ParamsException;
import com.cvte.bom.service.MdItemService;
import com.cvte.bom.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: 前端控制器
 */
@Controller
@RequestMapping("/mdItem")
public class MdItemController {

    @Autowired
    private MdItemService mdItemService;

    /**
     * 通过itemId获取MdItem数据
     *
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping("getMdItemByItemId")
    public R getMdItemByItemId(Integer itemId) {
        //参数校验
        if (itemId == null || itemId == 0) {
            throw new ParamsException("请输入有效参数!");
        }
        return R.success(mdItemService.getById(itemId));
    }

    /**
     * 通过itemId获取MdItem树形数据
     *
     * @param itemId
     * @param invisible 设置item_class_code是否可见
     * @param level     设置可视层级
     * @return
     */
    @ResponseBody
    @RequestMapping("getMdItemTreeById")
    public R getMdItemTreeById(Integer itemId, String[] invisible, Integer level) {
        //参数校验
        if (itemId == null || itemId == 0) {
            throw new ParamsException("请输入有效参数!");
        }
        return R.success(mdItemService.getMdItemTreeById(itemId, invisible, level));
    }

    /**
     * 通过itemCode获取MdItem的成品路径
     *
     * @param code
     * @return 例：004.003 --> 002.01 --> 001.01
     */
    @ResponseBody
    @RequestMapping("getMdItemTraceByCode")
    public R getMdItemTraceByCode(String code) {
        //参数处理和校验
        String[] strings = code.split("\\.");
        if (StringUtils.isEmpty(code) || strings.length != 2) {
            throw new ParamsException("请输入有效参数!");
        }
        return R.success(mdItemService.getMdItemTraceById(strings));
    }

    /**
     * 通过多个id获取采购清单，逗号分割
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("getMdItemListByIds")
    public R getMdItemListByIds(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            throw new ParamsException("请输入有效参数!");
        }
        return R.success(mdItemService.getMdItemListByIds(ids));
    }

}

