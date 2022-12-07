package com.cvte.bom.service.impl;

import com.cvte.bom.service.MdItemRelaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Jonil
 * @Date: 2022/12/7 10:41
 * @Description:
 */
@SpringBootTest
class MdItemRelaServiceTest {

    @Autowired
    private MdItemRelaService mdItemRelaService;

    @Test
    void selectByPid() {
        System.out.println(mdItemRelaService.selectByPid(1));
    }

    @Test
    void selectByCid() {
        System.out.println(mdItemRelaService.selectByCid(2));
    }

    @Test
    void selectByPidAndCheck() {
        System.out.println(mdItemRelaService.selectByPid(1));
    }

    @Test
    void selectByCidsAndCheck() {
        Integer[] ids = new Integer[]{6, 9};
        System.out.println(mdItemRelaService.selectByCidsAndCheck(ids));
    }
}