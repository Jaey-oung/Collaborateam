package com.collaborateam.www.dao;

import com.collaborateam.www.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.UserMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<UserDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public void deleteAll() throws Exception {
        session.delete(namespace + "deleteAll");
    }

    @Override
    public int insert(UserDto userDto) throws Exception {
        return session.insert(namespace + "insert", userDto);
    }   // Create - C

    @Override
    public UserDto select(String id) throws Exception {
        return session.selectOne(namespace + "select", id);
    }   // Read - R

    @Override
    public int update(UserDto userDto) throws Exception {
        return session.update(namespace + "update", userDto);
    }   // Update - U

    @Override
    public int delete(String id) throws Exception {
        return session.delete(namespace + "delete", id);
    }   // Delete - D
}