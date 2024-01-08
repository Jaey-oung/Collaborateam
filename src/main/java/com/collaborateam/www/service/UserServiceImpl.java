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
    public int getCount() throws Exception {
        return userDao.count();
    }

    @Override
    public List<UserDto> getList() throws Exception {
        return userDao.selectAll();
    }

    @Override
    public void removeAllUsers() throws Exception {
        userDao.deleteAll();
    }

    @Override
    public int addUser(UserDto userDto) throws Exception {
        return userDao.insert(userDto);
    }   // Create - C

    @Override
    public UserDto getUser(String id) throws Exception {
        return userDao.select(id);
    }   // Read - R

    @Override
    public int updateUser(UserDto userDto) throws Exception {
        return userDao.update(userDto);
    }   // Update - U

    @Override
    public int removeUser(String id) throws Exception {
        return userDao.delete(id);
    }   // Delete - D
}
