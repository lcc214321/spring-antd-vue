package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.modules.sso.dao.SsoUserDao;
import com.eaton.modules.sso.entity.SsoUserEntity;
import com.eaton.modules.sso.service.SsoUserService;
import org.springframework.stereotype.Service;


/**
 * 系统用户
 *
 * @author qiaogqiang@163.com
 */
@Service("sysUserService")
public class SsoUserServiceImpl extends ServiceImpl<SsoUserDao, SsoUserEntity> implements SsoUserService {
    @Override
    public SsoUserEntity queryByMobile(String mobile) {
        return baseMapper.queryByMobile(mobile);
    }
}