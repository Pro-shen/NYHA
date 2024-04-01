package com.nyha.system.service;

import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;

import java.util.List;

public interface ITFollowApprovalService {

    public List<TFollow> list(TFollow tFollow);

    public SysUser getUserId(String userName);

    public int add(TFollow tFollow);

    public List<TFollow> selectTVisitListById(Long id);

    public int edit(TFollow tFollow);

    public TFollow remove(TFollow tFollow);

}
