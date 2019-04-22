package com.eaton.modules.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sso_user_depart")
public class SsoUserDepartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**主键id*/
    @TableId(type = IdType.UUID)
	private String id;
	/**用户id*/
	private String userId;
	/**部门id*/
	private String depId;
	public SsoUserDepartEntity(String id, String userId, String depId) {
		super();
		this.id = id;
		this.userId = userId;
		this.depId = depId;
	}
}
