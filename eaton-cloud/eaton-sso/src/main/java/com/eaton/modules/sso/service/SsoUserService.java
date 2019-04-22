package com.eaton.modules.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eaton.modules.sso.entity.SsoUserEntity;


/**
 * 系统用户
 *
 * @author qiaogqiang@163.com
 */
public interface SsoUserService extends IService<SsoUserEntity> {
    /**
     * 根据用户名(手机号)查询用户
     * @param username
     * @return
     */
    SsoUserEntity queryByMobile(String username);
}
