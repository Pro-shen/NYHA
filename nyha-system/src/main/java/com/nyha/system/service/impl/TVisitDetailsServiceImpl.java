package com.nyha.system.service.impl;

import com.nyha.common.annotation.DataScope;
import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TVisit;
import com.nyha.system.mapper.SysUserMapper;
import com.nyha.system.mapper.TVisitDetailsMapper;
import com.nyha.system.service.ITVisitDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TVisitDetailsServiceImpl implements ITVisitDetailsService {

    @Autowired
    private TVisitDetailsMapper tVisitDetailsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @DataScope(deptAlias = "tv")
    public List<TVisit> list(TVisit tVisit) {
        return tVisitDetailsMapper.list(tVisit);
    }

    @Override
    public SysUser getUserId(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    public int add(TVisit tVisit) {
        return tVisitDetailsMapper.add(tVisit);
    }

    @Override
    public List<TVisit> selectTVisitListById(Long id) {
        return tVisitDetailsMapper.selectTVisitListById(id);
    }

    @Override
    public int edit(TVisit tVisit) {
        return tVisitDetailsMapper.edit(tVisit);
    }

    @Override
    public TVisit remove(TVisit tVisit) {
        List<Long> resByIdsList = new ArrayList<>();
        TVisit tVisit1 = new TVisit();
        if (tVisit.getId() != null) {
            int res = tVisitDetailsMapper.remove(tVisit);
            if (res == 0) {
                resByIdsList.add(tVisit.getId());
                tVisit1.setIds(resByIdsList);
                return tVisit1;
            } else {
                tVisit1.setIds(resByIdsList);
                return tVisit1;
            }
        } else if (!tVisit.getIds().isEmpty()) {
            int resById;
            for (int i = 0; i < tVisit.getIds().size(); i++) {
                resById = tVisitDetailsMapper.removeByIds(tVisit.getIds().get(i));
                if (resById == 0) {
                    resByIdsList.add(tVisit.getIds().get(i));
                }
            }
            tVisit1.setIds(resByIdsList);
            return tVisit1;
        } else {
            resByIdsList.add(-1L);
            tVisit1.setIds(resByIdsList);
            return tVisit1;
        }
    }

}
