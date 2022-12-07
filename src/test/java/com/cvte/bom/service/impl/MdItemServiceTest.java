package com.cvte.bom.service.impl;

import com.cvte.bom.service.MdItemService;
import com.cvte.bom.vo.MdItemTreeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Jonil
 * @Date: 2022/12/7 10:54
 * @Description:
 */
@SpringBootTest
class MdItemServiceTest {

    @Autowired
    private MdItemService mdItemService;

    @Test
    void getMdItemTreeById() {
        System.out.println(mdItemService.getMdItemTreeById(1, new String[]{"008"}));
    }

    @Test
    void genTree() {
        MdItemTreeVO tree = mdItemService.getMdItemTreeById(1, null);
        StringBuilder res = new StringBuilder();
        mdItemService.genTree(tree, res, -1);
        System.out.println(res);
    }

    @Test
    void getMdItemTraceById() {
        System.out.println(mdItemService.getMdItemTraceById(new String[]{"004", "02"}));
    }

    @Test
    void getMdItemListByIds() {
        System.out.println(mdItemService.getMdItemListByIds(new Integer[]{6,9}));
    }
}