package com.nyha.system.service.impl;

import com.nyha.common.annotation.DataScope;
import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;
import com.nyha.system.domain.TVisit;
import com.nyha.system.mapper.SysUserMapper;
import com.nyha.system.mapper.TFollowDetailsMapper;
import com.nyha.system.mapper.TVisitDetailsMapper;
import com.nyha.system.service.ITFollowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TFollowDetailsServiceImpl implements ITFollowDetailsService {

    @Autowired
    private TFollowDetailsMapper tFollowDetailsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TVisitDetailsMapper tVisitDetailsMapper;

    @Override
    @DataScope(deptAlias = "tf")
    public List<TFollow> list(TFollow tFollow) {
        return tFollowDetailsMapper.list(tFollow);
    }

    @Override
    public SysUser getUserId(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    public int add(TFollow tFollow) {
        return tFollowDetailsMapper.add(tFollow);
    }

    @Override
    public List<TFollow> selectTVisitListById(Long id) {
        return tFollowDetailsMapper.selectTFollowListById(id);
    }

    @Override
    public int edit(TFollow tFollow) {
        TVisit tVisit = new TVisit();
        tVisit.setPatientId(tFollow.getPatientId());
        tVisit.setPatientName(tFollow.getPatientName());
        tVisit.setDoctorId(tFollow.getDoctorId());
        tVisit.setDoctorName(tFollow.getDoctorName());
        tVisit.setNyha(tFollow.getNyha());
        tVisit.setIncentive(tFollow.getIncentive());
        tVisit.setResults(tFollow.getResults());
        tVisit.setPsychology(tFollow.getPsychology());
        tVisit.setCompliance(tFollow.getCompliance());
        tVisit.setSymptoms(tFollow.getSymptoms());
        tVisit.setIsState(1);
        tVisit.setLifeStyle(tFollow.getLifeStyle());
        tVisit.setVisitStatus(tFollow.getVisitStatus());
        int res = tVisitDetailsMapper.add(tVisit);
        if(res == 1){
            List<TVisit> tVisit1 = tVisitDetailsMapper.selectTVisitList(tVisit);
            if(tVisit1.size() > 0){
                tFollow.setVisitId(tVisit1.get(0).getId());
                return tFollowDetailsMapper.edit(tFollow);
            }else {
                return -2;
            }
        }else{
            return -1;
        }

    }

    @Override
    public TFollow remove(TFollow tFollow) {
        List<Long> resByIdsList = new ArrayList<>();
        TFollow tFollow1 = new TFollow();
        if (tFollow.getId() != null) {
            int res = tFollowDetailsMapper.remove(tFollow);
            if (res == 0) {
                resByIdsList.add(tFollow.getId());
                tFollow1.setIds(resByIdsList);
                return tFollow1;
            } else {
                tFollow1.setIds(resByIdsList);
                return tFollow1;
            }
        } else if (!tFollow.getIds().isEmpty()) {
            int resById;
            for (int i = 0; i < tFollow.getIds().size(); i++) {
                resById = tFollowDetailsMapper.removeByIds(tFollow.getIds().get(i));
                if (resById == 0) {
                    resByIdsList.add(tFollow.getIds().get(i));
                }
            }
            tFollow1.setIds(resByIdsList);
            return tFollow1;
        } else {
            resByIdsList.add(-1L);
            tFollow1.setIds(resByIdsList);
            return tFollow1;
        }
    }

}
