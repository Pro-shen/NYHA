package com.nyha.system.service.impl;

import com.nyha.common.annotation.DataScope;
import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;
import com.nyha.system.mapper.SysUserMapper;
import com.nyha.system.mapper.TFollowRequestMapper;
import com.nyha.system.service.ITFollowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TFollowRequestServiceImpl implements ITFollowRequestService {

    @Autowired
    private TFollowRequestMapper tFollowRequestMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @DataScope(deptAlias = "tf")
    public List<TFollow> list(TFollow tFollow) {
        return tFollowRequestMapper.list(tFollow);
    }

    @Override
    public SysUser getUserId(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }
}
