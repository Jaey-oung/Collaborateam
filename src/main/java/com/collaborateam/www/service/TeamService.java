package com.collaborateam.www.service;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;

import java.util.List;

public interface TeamService {
    int getCount() throws Exception;
    List<TeamDto> getList() throws Exception;
    void removeAllTeams() throws Exception;
    int create(TeamDto teamDto) throws Exception;
    TeamDto read(Integer tno) throws Exception;
    int modify(TeamDto teamDto) throws Exception;
    int remove(Integer tno, String leader) throws Exception;
    List<TeamDto> getTeamPage(String id, TeamListCondition tlc) throws Exception;
    int getTeamCnt(String id) throws Exception;
    List<TeamDto> retrieveLeaderTeam(String id) throws Exception;
}