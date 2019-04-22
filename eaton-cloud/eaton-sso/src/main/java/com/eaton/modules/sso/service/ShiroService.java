package com.eaton.modules.sso.service;

import com.eaton.modules.sso.entity.SsoUserEntity;
import com.eaton.modules.sso.entity.SsoUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(SsoUserEntity user);

    SsoUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SsoUserEntity queryUser(String userId);
}