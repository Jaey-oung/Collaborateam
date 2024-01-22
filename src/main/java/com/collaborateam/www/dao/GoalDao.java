package com.collaborateam.www.dao;

import com.collaborateam.www.domain.GoalDto;

import java.util.List;

public interface GoalDao {
    int count(Integer tno) throws Exception;
    List<GoalDto> selectAll(Integer tno) throws Exception;
    int deleteAll(Integer tno);
    int insert(GoalDto goalDto) throws Exception;
    GoalDto select(Integer gno) throws Exception;
    int update(GoalDto goalDto) throws Exception;
    int delete(Integer gno) throws Exception;
}