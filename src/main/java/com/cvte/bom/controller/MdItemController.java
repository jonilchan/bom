package com.cvte.bom.controller;

import com.alibaba.fastjson.JSON;
import com.cvte.bom.exception.ParamsException;
import com.cvte.bom.service.MdItemService;
import com.cvte.bom.utils.R;
import com.cvte.bom.vo.MdItemTreeVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @return
     */
    @ResponseBody
    @RequestMapping("getMdItemTreeById")
    public R getMdItemTreeById(Integer itemId, String[] invisible) {
        //参数校验
        if (itemId == null || itemId == 0) {
            throw new ParamsException("请输入有效参数!");
        }
        //直接转换成json字符串,方便前端展示
        return R.success(JSON.toJSON(mdItemService.getMdItemTreeById(itemId, invisible)).toString());
    }

    /**
     * 通过itemId获取MdItem树形数据（String目录树形式）
     *
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping("getMdItemTreeStringById")
    public R getMdItemTreeStringById(Integer itemId, String[] invisible) {
        //参数校验
        if (itemId == null || itemId == 0) {
            throw new ParamsException("请输入有效参数!");
        }
        //获取树形结构
        MdItemTreeVO tree = mdItemService.getMdItemTreeById(itemId, invisible);
        //树的字符拼接builder
        StringBuilder stringBuilder = new StringBuilder();
        //生成目录树
        mdItemService.genTree(tree, stringBuilder, -1);
        return R.success(stringBuilder.toString());
    }

    /**
     * 通过itemCode获取MdItem的成品路径
     *
     * @param code
     * @return 例：004.03 --> 002.01 --> 001.01
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

