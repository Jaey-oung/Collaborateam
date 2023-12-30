package com.collaborateam.www.dao;

import com.collaborateam.www.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.UserMapper.";

    @Override
    public int countUser() throws Exception {
        return session.selectOne(namespace + "countUser");
    }

    @Override
    public void deleteAllUser() throws Exception {
        session.delete(namespace + "deleteAllUser");
    }

    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return session.insert(namespace + "insertUser", userDto);
    } // Create - C
}