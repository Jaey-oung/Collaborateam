package com.collaborateam.www.service;

import com.collaborateam.www.domain.UserDto;

import java.util.List;

public interface UserService {
    int countUser() throws Exception;
    List<UserDto> selectAllUser() throws Exception;
    void deleteAllUser() throws Exception;
    int insertUser(UserDto userDto) throws Exception;
    UserDto selectUser(String id) throws Exception;
    int updateUser(UserDto userDto) throws Exception;
    int deleteUser(String id) throws Exception;
}