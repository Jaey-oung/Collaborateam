package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;

import java.util.List;

public interface TeamDao {
    int count() throws Exception;
    List<TeamDto> selectAll() throws Exception;
    void removeAll() throws Exception;
    int insert(TeamDto teamDto) throws Exception;
    TeamDto select(Integer tno) throws Exception;
    int modify(TeamDto teamDto) throws Exception;
    int remove(Integer tno, String leader) throws Exception;
    List<TeamDto> teamPage(String id, TeamListCondition tlc) throws Exception;
    int teamCnt(String id) throws Exception;
    List<TeamDto> selectLeaderTeam(String id) throws Exception;
}