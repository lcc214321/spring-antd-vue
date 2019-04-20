package com.eaton.modules.sys.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "系统用户管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @GetMapping("/info")
    public String info() {
        return "hello";
    }
}
