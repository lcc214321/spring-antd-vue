package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.modules.sso.dao.SsoPermissionDao;
import com.eaton.modules.sso.entity.SsoPermissionEntity;
import com.eaton.modules.sso.service.SsoPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限服务
 * @author qiaogqiang@163.com
 */
@Service("ssoPermissionService")
public class SsoPermissionServiceImpl extends ServiceImpl<SsoPermissionDao, SsoPermissionEntity> implements SsoPermissionService {
}