package com.eaton.modules.sso.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eaton.modules.sso.entity.SsoUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author qiaogqiang@163.com
 */
@Mapper
public interface SsoUserTokenDao extends BaseMapper<SsoUserTokenEntity> {
    SsoUserTokenEntity queryByToken(String token);
    SsoUserTokenEntity getTerminalToken(String userId, Long terminal);
}
