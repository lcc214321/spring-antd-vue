package com.eaton.modules.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sso_user_token")
public class SsoUserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户ID
	@TableId(type = IdType.INPUT)
	private Long id;

	private String userId;
	//token
	private String token;
	//过期时间
	private Date expireTime;
	//更新时间
	private Date updateTime;

	// 终端类型
	private Long terminal;
}
