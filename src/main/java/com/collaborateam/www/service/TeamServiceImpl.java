package com.collaborateam.www.service;

import com.collaborateam.www.dao.TeamDao;
import com.collaborateam.www.domain.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;

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
    public int create(TeamDto teamDto) throws Exception {
        return teamDao.insert(teamDto);
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
}
