package com.collaborateam.www.service;

import com.collaborateam.www.domain.GoalDto;

import java.util.List;

public interface GoalService {
    int getCount(Integer tno) throws Exception;
    List<GoalDto> getList(Integer tno) throws Exception;
    void deleteAll(Integer tno);
    int create(GoalDto goalDto) throws Exception;
    GoalDto read(Integer gno) throws Exception;
    int update(GoalDto goalDto) throws Exception;
    int delete(Integer gno) throws Exception;
}