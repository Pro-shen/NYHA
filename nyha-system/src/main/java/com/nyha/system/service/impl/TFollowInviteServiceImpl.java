package com.nyha.system.service.impl;

import com.nyha.common.annotation.DataScope;
import com.nyha.common.core.domain.entity.SysUser;
import com.nyha.system.domain.TFollow;
import com.nyha.system.mapper.SysUserMapper;
import com.nyha.system.mapper.TFollowInviteMapper;
import com.nyha.system.service.ITFollowInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TFollowInviteServiceImpl implements ITFollowInviteService {

    @Autowired
    private TFollowInviteMapper tFollowInviteMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @DataScope(deptAlias = "tf")
    public List<TFollow> list(TFollow tFollow) {
        return tFollowInviteMapper.list(tFollow);
    }

    @Override
    public SysUser getUserId(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    @DataScope(deptAlias = "u")
    public List<SysUser> selectUserList(TFollow tFollow) {
        return sysUserMapper.selectUser(tFollow);
    }

    @Override
    public int add(TFollow tFollow) {
        return tFollowInviteMapper.add(tFollow);
    }

    @Override
    public List<TFollow> selectTVisitListById(Long id) {
        List<TFollow> tFollows = tFollowInviteMapper.selectTFollowListById(id);
        tFollows.get(0).setNickName(tFollows.get(0).getPatientName());
        tFollows.get(0).setUserId(tFollows.get(0).getPatientId());
        return tFollows;
    }

    @Override
    public int edit(TFollow tFollow) {
        return tFollowInviteMapper.edit(tFollow);
    }

    @Override
    public TFollow remove(TFollow tFollow) {
        List<Long> resByIdsList = new ArrayList<>();
        TFollow tFollow1 = new TFollow();
        if (tFollow.getId() != null) {
            int res = tFollowInviteMapper.remove(tFollow);
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
                resById = tFollowInviteMapper.removeByIds(tFollow.getIds().get(i));
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
