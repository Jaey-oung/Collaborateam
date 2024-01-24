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
    public int create(UserDto userDto) throws Exception {
        return userDao.insert(userDto);
    }

    @Override
    public UserDto read(String id) throws Exception {
        return userDao.select(id);
    }

    @Override
    public int update(UserDto userDto) throws Exception {
        return userDao.modify(userDto);
    }

    @Override
    public int delete(String id) throws Exception {
        return userDao.remove(id);
    }
}
