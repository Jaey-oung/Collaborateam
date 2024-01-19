package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TeamDto;

import java.util.List;

public interface TeamDao {
    int count() throws Exception;
    List<TeamDto> selectAll() throws Exception;
    List<TeamDto> selectUserTeam(String leader) throws Exception;
    void deleteAll() throws Exception;
    int insert(TeamDto teamDto) throws Exception;  // Create - C
    TeamDto select(Integer tno) throws Exception;  // Read - R
    int update(TeamDto teamDto) throws Exception;  // Update - U
    int delete(Integer tno, String leader) throws Exception;    // Delete - D
}