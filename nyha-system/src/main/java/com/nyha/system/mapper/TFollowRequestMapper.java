package com.nyha.system.mapper;

import com.nyha.system.domain.TFollow;
import com.nyha.system.domain.TVisit;

import java.util.List;

public interface TFollowRequestMapper {

    public List<TFollow> list(TFollow tFollow);

}
