package com.eaton.modules.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eaton.common.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 部门表
 */
@Data
@TableName("sso_depart")
public class SsoDepartEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**父机构ID*/
	private String parentId;
	/**机构/部门名称*/
	private String departName;
	/**英文名*/
	private String departNameEn;
	/**缩写*/
	private String departNameAbbr;
	/**排序*/
	private Integer orderNum;
	/**描述*/
	private Object description;
	/**机构类型*/
	private String orgType;
	/**机构编码*/
	private String orgCode;
	/**手机号*/
	private String mobile;
	/**传真*/
	private String fax;
	/**地址*/
	private String address;
	/**备注*/
	private String memo;
	/**状态（1启用，0不启用）*/
	@Dict(dicCode = "depart_status")
	private String status;

	/**删除状态（0，正常，1已删除）*/
	@Dict(dicCode = "del_flag")
	private String delFlag;
	/**创建人*/
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/**
	 * 重写equals方法
	 */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        if (!super.equals(o)) {
			return false;
		}
		SsoDepartEntity depart = (SsoDepartEntity) o;
        return Objects.equals(id, depart.id) &&
                Objects.equals(parentId, depart.parentId) &&
                Objects.equals(departName, depart.departName) &&
                Objects.equals(departNameEn, depart.departNameEn) &&
                Objects.equals(departNameAbbr, depart.departNameAbbr) &&
                Objects.equals(orderNum, depart.orderNum) &&
                Objects.equals(description, depart.description) &&
                Objects.equals(orgType, depart.orgType) &&
                Objects.equals(orgCode, depart.orgCode) &&
                Objects.equals(mobile, depart.mobile) &&
                Objects.equals(fax, depart.fax) &&
                Objects.equals(address, depart.address) &&
                Objects.equals(memo, depart.memo) &&
                Objects.equals(status, depart.status) &&
                Objects.equals(delFlag, depart.delFlag) &&
                Objects.equals(createBy, depart.createBy) &&
                Objects.equals(createTime, depart.createTime) &&
                Objects.equals(updateBy, depart.updateBy) &&
                Objects.equals(updateTime, depart.updateTime);
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, parentId, departName, 
        		departNameEn, departNameAbbr, orderNum, description,
        		orgType, orgCode, mobile, fax, address, memo, status, 
        		delFlag, createBy, createTime, updateBy, updateTime);
    }
}
