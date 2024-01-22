package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TaskDto;

import java.util.List;

public interface TaskDao {
    int count(Integer tno) throws Exception;
    List<TaskDto> selectAll(Integer tno, String member) throws Exception;
    int deleteAll(Integer tno);
    int insert(TaskDto taskDto) throws Exception;
    TaskDto select(Integer tano) throws Exception;
    int update(TaskDto taskDto) throws Exception;
    int delete(Integer tano) throws Exception;
}