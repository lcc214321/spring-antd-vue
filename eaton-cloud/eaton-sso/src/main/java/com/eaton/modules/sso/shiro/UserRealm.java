package com.eaton.modules.sso.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eaton.modules.sso.dao.SsoPermissionDao;
import com.eaton.modules.sso.dao.SsoUserDao;
import com.eaton.modules.sso.entity.SsoPermissionEntity;
import com.eaton.modules.sso.entity.SsoUserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Step 1: 配置自定义Ream
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SsoUserDao userDao;

    @Autowired
    private SsoPermissionDao permissionDao;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SsoUserEntity user = (SsoUserEntity)principals.getPrimaryPrincipal();
        String userId = user.getId();

        List<String> permsList;

        // 超级管理员: 拥有最高权限
        if (user.getSupperFlag() > 0) {
            List<SsoPermissionEntity> permissions = permissionDao.selectList(null);
            permsList = new ArrayList<>(permissions.size());
            for (SsoPermissionEntity permission: permissions) {
                permsList.add(permission.getPerms());
            }
        } else {
            permsList = userDao.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登陆时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

        //查询用户信息
        SsoUserEntity user = userDao.selectOne(new QueryWrapper<SsoUserEntity>().eq("mobile", token.getUsername()));
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if(user.getIsEnabled() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
