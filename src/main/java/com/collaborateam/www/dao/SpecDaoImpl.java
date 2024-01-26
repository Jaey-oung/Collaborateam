package com.collaborateam.www.dao;

import com.collaborateam.www.domain.SpecDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecDaoImpl implements SpecDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.SpecMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<SpecDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public void removeAll() throws Exception {
        session.delete(namespace+"removeAll");
    }

    @Override
    public int insert(SpecDto specDto) throws Exception {
        return session.insert(namespace+"insert", specDto);
    }

    @Override
    public SpecDto select(Integer sno) throws Exception {
        return session.selectOne(namespace + "select", sno);
    }

    @Override
    public int modify(SpecDto specDto) throws Exception {
        return session.update(namespace+"modify", specDto);
    }

    @Override
    public int remove(Integer sno) throws Exception {
        return session.delete(namespace+"remove", sno);
    }

    @Override
    public List<SpecDto> selectSpecsByField(String field) throws Exception {
        return session.selectList(namespace+"selectSpecsByField", field);
    }

}