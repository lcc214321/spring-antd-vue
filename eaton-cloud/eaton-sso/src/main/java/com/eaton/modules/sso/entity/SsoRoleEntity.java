package com.eaton.modules.sso.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author qiaogqiang@163.com
 */
@Data
@TableName("sso_role")
public class SsoRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId(type = IdType.UUID)
	private String id;

	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	private String roleName;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
