package com.collaborateam.www.dao;

import com.collaborateam.www.domain.FieldDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FieldDaoImpl implements FieldDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.FieldMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<FieldDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public void removeAll() throws Exception {
        session.delete(namespace+"removeAll");
    }

    @Override
    public int insert(FieldDto fieldDto) throws Exception {
        return session.insert(namespace+"insert", fieldDto);
    }

    @Override
    public FieldDto select(Integer fno) throws Exception {
        return session.selectOne(namespace + "select", fno);
    }

    @Override
    public int modify(FieldDto fieldDto) throws Exception {
        return session.update(namespace+"modify", fieldDto);
    }

    @Override
    public int remove(Integer fno) throws Exception {
        return session.delete(namespace+"remove", fno);
    }
}