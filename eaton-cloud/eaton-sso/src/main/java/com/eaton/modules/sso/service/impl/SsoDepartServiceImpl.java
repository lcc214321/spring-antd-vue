package com.eaton.modules.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eaton.modules.sso.entity.SsoDepartEntity;
import com.eaton.modules.sso.dao.SsoDepartDao;
import com.eaton.modules.sso.service.SsoDepartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ssoDepartService")
public class SsoDepartServiceImpl extends ServiceImpl<SsoDepartDao, SsoDepartEntity> implements SsoDepartService {

    @Override
    public List<SsoDepartEntity> queryList(Map<String, Object> map) {
        return baseMapper.queryList(map);
    }
}
