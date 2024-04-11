package com.nyha.system.service;

import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;

import java.util.List;

public interface ITFollowInviteService {

    public List<TFollow> list(TFollow tFollow);

    public SysUser getUserId(String userName);

    public List<SysUser> selectUserList(TFollow tFollow);

    public int add(TFollow tFollow);

    public List<TFollow> selectTVisitListById(Long id);

    public int edit(TFollow tFollow);

    public TFollow remove(TFollow tFollow);

}
