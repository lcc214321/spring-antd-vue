package com.eaton.modules.sso.form;

import lombok.Data;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class SsoLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}