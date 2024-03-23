package com.nyha.system.mapper;

import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TVisit;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface TVisitRequestMapper {

    public List<TVisit> list(TVisit tVisit);

    public int add(TVisit tVisit);

    public List<TVisit> selectTVisitListById(Long id);

    public int edit(TVisit tVisit);

    public int remove(TVisit tVisit);

    public int removeByIds(@Param("id")Long id);

}
