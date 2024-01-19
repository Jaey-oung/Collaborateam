package com.collaborateam.www.service;

import com.collaborateam.www.domain.TeamDto;

import java.util.List;

public interface TeamService {
    int getCount() throws Exception;
    List<TeamDto> getList() throws Exception;
    List<TeamDto> getUserTeam(String leader) throws Exception;
    void removeAllTeams() throws Exception;
    int create(TeamDto teamDto) throws Exception;
    TeamDto read(Integer tno) throws Exception;
    int modify(TeamDto teamDto) throws Exception;
    int remove(Integer tno, String leader) throws Exception;
}