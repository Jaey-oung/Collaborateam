package com.collaborateam.www.dao;


import com.collaborateam.www.domain.GoalDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalDaoImpl implements GoalDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.GoalMapper.";

    @Override
    public int count(Integer tno) throws Exception {
        return session.selectOne(namespace+"count", tno);
    }

    @Override
    public List<GoalDto> selectAll(Integer tno) throws Exception {
        return session.selectList(namespace+"selectAll", tno);
    }

    @Override
    public void removeAll(Integer tno) {
        session.delete(namespace+"removeAll", tno);
    }

    @Override
    public int insert(GoalDto goalDto) throws Exception {
        return session.insert(namespace+"insert", goalDto);
    }

    @Override
    public GoalDto select(Integer gno) throws Exception {
        return session.selectOne(namespace+"select", gno);
    }

    @Override
    public int modify(GoalDto goalDto) throws Exception {
        return session.update(namespace+"modify", goalDto);
    }

    @Override
    public int remove(Integer gno) throws Exception {
        return session.delete(namespace+"remove", gno);
    }
}