package com.eaton.modules.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eaton.common.validator.group.AddGroup;
import com.eaton.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @author qiaogqiang@163.com
 */
@Data
@TableName("sso_user")
public class SsoUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 用户ID
	 */
	@TableId(type = IdType.UUID)
	private String id;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 性别: 1：男 2：女 3: Unknown
	 */
	private Integer sex;
	/**
	 * 是否启用: 1 启用 2 冻结
	 */
	private Integer isEnabled;
	/**
	 * 删除标志: 0 未删除 1 已删除
	 */
	private Integer delFlag;

	/**
	 * 超级管理员标志
	 */
	private Integer supperFlag;

	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
}
