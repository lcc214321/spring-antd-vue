package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoDictEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SsoDictDao extends BaseMapper<SsoDictEntity> {
    String queryTableDictTextByKey(String table,String text,String code, String key);
    String queryDictTextByKey(String code, String key);
}
