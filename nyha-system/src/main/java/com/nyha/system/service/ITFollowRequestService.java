package com.nyha.system.service;

import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;
import com.nyha.system.domain.TVisit;

import java.util.List;

public interface ITFollowRequestService {

    public List<TFollow> list(TFollow tFollow);

    public SysUser getUserId(String userName);

}
