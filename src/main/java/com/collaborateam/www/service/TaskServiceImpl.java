package com.collaborateam.www.service;

import com.collaborateam.www.dao.TaskDao;
import com.collaborateam.www.domain.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;

    @Override
    public int getCount(Integer tno) throws Exception {
        return taskDao.count(tno);
    }

    @Override
    public List<TaskDto> getList(Integer tno, String member) throws Exception {
        return taskDao.selectAll(tno, member);
    }

    @Override
    public void removeAllTasks(Integer tno) {
        taskDao.deleteAll(tno);
    }

    @Override
    public int create(TaskDto taskDto) throws Exception {
        return taskDao.insert(taskDto);
    }

    @Override
    public TaskDto read(Integer tano) throws Exception {
        return taskDao.select(tano);
    }

    @Override
    public int modify(TaskDto taskDto) throws Exception {
        return taskDao.update(taskDto);
    }

    @Override
    public int remove(Integer tano) throws Exception {
        return taskDao.delete(tano);
    }
}
