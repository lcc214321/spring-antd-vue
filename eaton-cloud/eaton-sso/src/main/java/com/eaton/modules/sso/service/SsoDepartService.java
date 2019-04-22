package com.eaton.modules.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eaton.modules.sso.entity.SsoDepartEntity;

import java.util.List;
import java.util.Map;

public interface SsoDepartService extends IService<SsoDepartEntity> {
    /**
     * 查询所有部门信息，并分节点进行显示
     */
    List<SsoDepartEntity> queryList(Map<String, Object> map);
}
