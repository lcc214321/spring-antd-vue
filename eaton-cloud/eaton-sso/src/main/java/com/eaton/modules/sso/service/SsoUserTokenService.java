package com.eaton.modules.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eaton.common.utils.R;
import com.eaton.modules.sso.entity.SsoUserTokenEntity;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SsoUserTokenService extends IService<SsoUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

	/**
	 * 根据token获取用户信息
	 * @param token
	 */
	SsoUserTokenEntity queryTokenByUserId(String token);
}
