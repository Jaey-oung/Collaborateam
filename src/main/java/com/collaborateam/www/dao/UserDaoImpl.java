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
    public int countUser() throws Exception {
        return session.selectOne(namespace + "countUser");
    }

    @Override
    public List<UserDto> selectAllUser() throws Exception {
        return session.selectList(namespace + "selectAllUser");
    }


    @Override
    public void deleteAllUser() throws Exception {
        session.delete(namespace + "deleteAllUser");
    }

    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return session.insert(namespace + "insertUser", userDto);
    } // Create - C

    @Override
    public UserDto selectUser(String id) throws Exception {
        return session.selectOne(namespace + "selectUser", id);
    } // Read - C

    @Override
    public int updateUser(UserDto userDto) throws Exception {
        return session.update(namespace + "updateUser", userDto);
    } // Update - U
}