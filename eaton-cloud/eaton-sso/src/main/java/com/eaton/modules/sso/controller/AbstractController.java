package com.eaton.modules.sso.controller;

import com.eaton.modules.sso.entity.SsoUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * Controller公共组件
 *
 * @author qiaogqiang@163.com
 */
@Slf4j
public abstract class AbstractController {
	protected SsoUserEntity getUser() {
		return (SsoUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected String getUserId() {
		return getUser().getId();
	}
}