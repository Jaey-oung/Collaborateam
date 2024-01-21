package com.collaborateam.www.service;

import com.collaborateam.www.dao.MemberDao;
import com.collaborateam.www.dao.TeamDao;
import com.collaborateam.www.domain.MemberDto;
import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    MemberDao memberDao;

    @Override
    public int getCount() throws Exception {
        return teamDao.count();
    }

    @Override
    public List<TeamDto> getList() throws Exception {
        return teamDao.selectAll();
    }

    @Override
    public void removeAllTeams() throws Exception {
        teamDao.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(TeamDto teamDto) throws Exception {
        int rowCnt = teamDao.insert(teamDto);
        if(rowCnt == 1) {
            MemberDto leader = new MemberDto(teamDto.getTno(), teamDto.getLeader(), "Leader");
            rowCnt = memberDao.insert(leader);
        }
        return rowCnt;
    }

    @Override
    public TeamDto read(Integer tno) throws Exception {
        return teamDao.select(tno);
    }

    @Override
    public int modify(TeamDto teamDto) throws Exception {
        return teamDao.update(teamDto);
    }

    @Override
    public int remove(Integer tno, String leader) throws Exception {
        return teamDao.delete(tno, leader);
    }

    @Override
    public List<TeamDto> getTeamPage(String id, TeamListCondition tlc) throws Exception {
        return teamDao.teamPage(id, tlc);
    }

    @Override
    public int getTeamCnt(String id) throws Exception {
        return teamDao.teamCnt(id);
    }

    @Override
    public List<TeamDto> retrieveLeaderTeam(String id) throws Exception {
        return teamDao.getLeaderTeam(id);
    }

}
