package com.cvte.bom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Jonil
 * @Date: 2022/12/5
 * @Description: 页面控制器
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @RequestMapping("/tree")
    public String treePage() {
        return "tree";
    }

    @RequestMapping("/notTree")
    public String notTreePage() {
        return "notTree";
    }

    @RequestMapping("/trace")
    public String tracePage() {
        return "trace";
    }

    @RequestMapping("/mdItemList")
    public String mdItemListPage() {
        return "list";
    }
}
