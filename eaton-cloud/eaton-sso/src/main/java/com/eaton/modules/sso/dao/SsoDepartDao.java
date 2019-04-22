package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoDepartEntity;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author qiaogqiang@163.com
 */
@Mapper
public interface SsoDepartDao extends BaseMapper<SsoDepartEntity> {

    public List<SsoDepartEntity> queryUserDeparts(String userId);
    public List<SsoDepartEntity> queryList(Map<String, Object> map);
}
