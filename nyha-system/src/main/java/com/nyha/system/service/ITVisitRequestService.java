package com.nyha.system.service;

import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TVisit;

import java.util.List;

public interface ITVisitRequestService {

    public List<TVisit> list(TVisit tVisit);

    public SysUser getUserId(String userName);

    public int add(TVisit tVisit);

    public List<TVisit> selectTVisitListById(Long id);

    public int edit(TVisit tVisit);

    public TVisit remove(TVisit tVisit);

}
