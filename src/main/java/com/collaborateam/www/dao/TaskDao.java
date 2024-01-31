package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TaskDto;

import java.util.List;

public interface TaskDao {
    int count(Integer tno) throws Exception;
    List<TaskDto> selectAll(Integer tno) throws Exception;
    void removeAll(Integer tno);
    int insert(TaskDto taskDto) throws Exception;
    TaskDto select(Integer tano) throws Exception;
    int modify(TaskDto taskDto) throws Exception;
    int remove(Integer tano, String member) throws Exception;
    List<TaskDto> selectMemberTask(Integer tno, String member) throws Exception;
}