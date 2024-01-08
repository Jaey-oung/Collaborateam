package com.collaborateam.www.service;

import com.collaborateam.www.domain.UserDto;

import java.util.List;

public interface UserService {
    int getCount() throws Exception;
    List<UserDto> getList() throws Exception;
    void removeAllUsers() throws Exception;
    int addUser(UserDto userDto) throws Exception;   // Create - C
    UserDto getUser(String id) throws Exception; // Read - R
    int updateUser(UserDto userDto) throws Exception;   // Update - U
    int removeUser(String id) throws Exception; // Delete - D
}