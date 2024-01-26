package com.collaborateam.www.service;

import com.collaborateam.www.domain.UserDto;

import java.util.List;

public interface UserService {
    int getCount() throws Exception;
    List<UserDto> getList() throws Exception;
    void deleteAll() throws Exception;
    int create(UserDto userDto) throws Exception;
    UserDto read(String id) throws Exception;
    int update(UserDto userDto) throws Exception;
    int delete(String id) throws Exception;
}