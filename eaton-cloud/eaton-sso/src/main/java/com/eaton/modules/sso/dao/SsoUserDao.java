package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author qiaogqiang@163.com
 */
@Mapper
public interface SsoUserDao extends BaseMapper<SsoUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(String userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(String userId);

	/**
	 * 根据用户手机，查询系统用户
	 */
	SsoUserEntity queryByMobile(String mobile);

	List<SsoUserEntity> queryList(Map<String, Object> map);
}
