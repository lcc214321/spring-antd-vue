package com.eaton.modules.sso.controller;


import com.eaton.common.utils.R;
import com.eaton.modules.sso.entity.SsoUserEntity;
import com.eaton.modules.sso.form.SsoLoginForm;
import com.eaton.modules.sso.service.SsoUserService;
import com.eaton.modules.sso.service.SsoUserTokenService;
import com.eaton.modules.sso.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

/**
 * 登录相关
 *
 * @author qiaogqiang@163.com
 */
@Api(tags = "登陆管理")
@RestController
public class SsoLoginController extends AbstractController {
	@Autowired
	private SsoUserService userService;

	@Autowired
	private SsoUserTokenService userTokenService;
	/**
	 * 登录
	 */
	@ApiOperation("登陆接口")
	@ResponseBody
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public R login(@RequestBody SsoLoginForm form) throws IOException {
		//用户信息 用户名更改为手机
		SsoUserEntity user = userService.queryByMobile(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(ShiroUtils.sha256(form.getPassword(), user.getSalt()))) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getIsEnabled() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = userTokenService.createToken(user.getId());
		return r;
	}

	/**
	 * 退出
	 * refer to: https://www.cnblogs.com/guochunyang2004/p/8629112.html
	 * spring boot 错误：Check your ViewResolver setup
	 */
	@ResponseBody
	@ApiOperation("登出接口")
	@PostMapping("/logout")
	public R logout() {
		userTokenService.logout(getUserId());
		return R.ok();
	}
}
