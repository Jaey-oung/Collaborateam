package com.collaborateam.www.service;

import com.collaborateam.www.domain.UserDto;

import java.util.List;

public interface UserService {
    int getCount() throws Exception;
    List<UserDto> getList() throws Exception;
    void removeAllUsers() throws Exception;
    int create(UserDto userDto) throws Exception;   // Create - C
    UserDto read(String id) throws Exception; // Read - R
    int update(UserDto userDto) throws Exception;   // Update - U
    int delete(String id) throws Exception; // Delete - D
}