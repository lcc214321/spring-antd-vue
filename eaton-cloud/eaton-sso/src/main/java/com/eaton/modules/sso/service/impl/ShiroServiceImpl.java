package com.eaton.modules.sso.service.impl;

import com.eaton.modules.sso.dao.SsoPermissionDao;
import com.eaton.modules.sso.dao.SsoUserDao;
import com.eaton.modules.sso.dao.SsoUserTokenDao;
import com.eaton.modules.sso.entity.SsoPermissionEntity;
import com.eaton.modules.sso.entity.SsoUserEntity;
import com.eaton.modules.sso.entity.SsoUserTokenEntity;
import com.eaton.modules.sso.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SsoPermissionDao permissionDao;
    @Autowired
    private SsoUserDao userDao;
    @Autowired
    private SsoUserTokenDao userTokenDao;

    @Override
    public Set<String> getUserPermissions(SsoUserEntity user) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(user.getSupperFlag() > 0){
            List<SsoPermissionEntity> menuList = permissionDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SsoPermissionEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = userDao.queryAllPerms(user.getId());
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SsoUserTokenEntity queryByToken(String token) {
        return userTokenDao.queryByToken(token);
    }

    @Override
    public SsoUserEntity queryUser(String userId) {
        return userDao.selectById(userId);
    }
}
