package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.common.utils.HttpContextUtils;
import com.eaton.common.utils.R;
import com.eaton.modules.sso.dao.SsoUserTokenDao;
import com.eaton.modules.sso.entity.SsoUserTokenEntity;
import com.eaton.modules.sso.oauth2.TokenGenerator;
import com.eaton.modules.sso.service.SsoUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SsoUserTokenServiceImpl extends ServiceImpl<SsoUserTokenDao, SsoUserTokenEntity> implements SsoUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Autowired
	SsoUserTokenDao userTokenDao;

	@Override
	public R createToken(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		Long terminal = HttpContextUtils.getTerminal();
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SsoUserTokenEntity tokenEntity = baseMapper.getTerminalToken(userId, terminal);
		if(tokenEntity == null){
			tokenEntity = new SsoUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setTerminal(terminal);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}

	@Override
	public void logout(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SsoUserTokenEntity tokenEntity = new SsoUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}

	@Override
	public SsoUserTokenEntity queryTokenByUserId(String token) {
		return userTokenDao.queryByToken(token);
	}
}
