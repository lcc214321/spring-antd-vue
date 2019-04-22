package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.modules.sso.dao.SsoDictDao;
import com.eaton.modules.sso.entity.SsoDictEntity;
import com.eaton.modules.sso.service.SsoDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service("ssoDictService")
public class SsoDictServiceImpl extends ServiceImpl<SsoDictDao, SsoDictEntity> implements SsoDictService {
    @Autowired
    private SsoDictDao dictDao;

    /**
     * 通过查询指定table的 text code 获取字典值text
     * dictTableCache采用redis缓存有效期10分钟
     * @param table
     * @param text
     * @param code
     * @param key
     * @return
     */
    @Override
    @Cacheable(value = "dictTableCache")
    public String queryTableDictTextByKey(String table,String text,String code, String key) {
        log.info("无缓存dictTable的时候调用这里！");
        return dictDao.queryTableDictTextByKey(table,text,code,key);
    }

    /**
     * 通过查询指定code 获取字典值text
     * @param code
     * @param key
     * @return
     */
    @Override
    @Cacheable(value = "dictCache")
    public String queryDictTextByKey(String code, String key) {
        log.info("无缓存dictText的时候调用这里！");
        return dictDao.queryDictTextByKey(code, key);
    }
}
