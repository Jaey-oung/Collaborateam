package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TaskDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskDaoImpl implements TaskDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.TaskMapper.";

    @Override
    public int count(Integer tno) throws Exception {
        return session.selectOne(namespace+"count", tno);
    }

    @Override
    public List<TaskDto> selectAll(Integer tno) throws Exception {
        return session.selectList(namespace+"selectAll", tno);
    }

    @Override
    public void removeAll(Integer tno) {
        session.delete(namespace+"removeAll", tno);
    }

    @Override
    public int insert(TaskDto taskDto) throws Exception {
        return session.insert(namespace+"insert", taskDto);
    }

    @Override
    public TaskDto select(Integer tano) throws Exception {
        return session.selectOne(namespace+"select", tano);
    }

    @Override
    public int modify(TaskDto taskDto) throws Exception {
        return session.update(namespace+"modify", taskDto);
    }

    @Override
    public int remove(Integer tano, String member) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tano", tano);
        map.put("member", member);
        return session.delete(namespace+"remove", map);
    }

    @Override
    public List<TaskDto> selectMemberTask(Integer tno, String member) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tno", tno);
        map.put("member", member);
        return session.selectList(namespace+"selectMemberTask", map);
    }
}