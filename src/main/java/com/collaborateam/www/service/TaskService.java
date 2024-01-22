package com.collaborateam.www.service;

import com.collaborateam.www.domain.TaskDto;

import java.util.List;

public interface TaskService {
    int getCount(Integer tno) throws Exception;
    List<TaskDto> getList(Integer tno, String member) throws Exception;
    void removeAllTasks(Integer tno);
    int create(TaskDto taskDto) throws Exception;
    TaskDto read(Integer tano) throws Exception;
    int modify(TaskDto taskDto) throws Exception;
    int remove(Integer tano) throws Exception;
}