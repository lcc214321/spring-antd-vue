package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理
 *
 * @author qiaogqiang@163.com
 */
@Mapper
public interface SsoRoleDao extends BaseMapper<SsoRoleEntity> {

}
