package com.collaborateam.www.service;

import com.collaborateam.www.domain.TaskDto;

import java.util.List;

public interface TaskService {
    int getCount(Integer tno) throws Exception;
    List<TaskDto> getList(Integer tno) throws Exception;
    void deleteAll(Integer tno);
    int create(TaskDto taskDto) throws Exception;
    TaskDto read(Integer tano) throws Exception;
    int update(TaskDto taskDto) throws Exception;
    int delete(Integer tano, String member) throws Exception;
    List<TaskDto> getMemberTask(Integer tno, String member) throws Exception;
}