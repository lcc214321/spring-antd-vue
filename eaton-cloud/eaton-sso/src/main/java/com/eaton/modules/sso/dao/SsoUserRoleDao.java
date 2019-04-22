package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author qiaogqiang@163.com
 */
@Mapper
public interface SsoUserRoleDao extends BaseMapper<SsoUserRoleEntity> {
}
