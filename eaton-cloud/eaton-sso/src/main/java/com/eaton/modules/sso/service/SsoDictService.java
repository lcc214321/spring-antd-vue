package com.eaton.modules.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eaton.modules.sso.entity.SsoDictEntity;


public interface SsoDictService extends IService<SsoDictEntity> {
    String queryTableDictTextByKey(String table, String text, String code, String key);
    String queryDictTextByKey(String code, String key);
}
