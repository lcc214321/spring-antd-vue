package com.eaton.modules.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author scott
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SsoUserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

	public SsoUserRoleEntity() {
	}

	public SsoUserRoleEntity(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}
}
