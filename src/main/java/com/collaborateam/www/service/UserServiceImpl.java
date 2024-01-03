package com.collaborateam.www.service;

import com.collaborateam.www.dao.UserDao;
import com.collaborateam.www.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int countUser() throws Exception {
        return userDao.count();
    }

    @Override
    public List<UserDto> selectAllUser() throws Exception {
        return userDao.selectAll();
    }

    @Override
    public void deleteAllUser() throws Exception {
        userDao.deleteAll();
    }

    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return userDao.insert(userDto);
    }

    @Override
    public UserDto selectUser(String id) throws Exception {
        return userDao.select(id);
    }

    @Override
    public int updateUser(UserDto userDto) throws Exception {
        return userDao.update(userDto);
    }

    @Override
    public int deleteUser(String id) throws Exception {
        return userDao.delete(id);
    }
}
