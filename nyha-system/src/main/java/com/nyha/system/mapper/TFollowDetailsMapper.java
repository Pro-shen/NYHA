package com.nyha.system.mapper;

import com.nyha.system.domain.TFollow;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface TFollowDetailsMapper {

    public List<TFollow> list(TFollow tFollow);

    public int add(TFollow tFollow);

    public List<TFollow> selectTFollowListById(Long id);

    public int edit(TFollow tFollow);

    public int remove(TFollow tFollow);

    public int removeByIds(@Param("id")Long id);

}
