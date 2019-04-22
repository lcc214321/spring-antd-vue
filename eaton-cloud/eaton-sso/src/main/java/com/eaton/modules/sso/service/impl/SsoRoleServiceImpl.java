package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.modules.sso.dao.SsoRoleDao;
import com.eaton.modules.sso.entity.SsoRoleEntity;
import com.eaton.modules.sso.service.SsoRoleService;
import org.springframework.stereotype.Service;


/**
 * 角色
 *
 * @author qiaogqiang@163.com
 */
@Service("ssoRoleService")
public class SsoRoleServiceImpl extends ServiceImpl<SsoRoleDao, SsoRoleEntity> implements SsoRoleService {

}
