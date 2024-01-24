package com.collaborateam.www.dao;

import com.collaborateam.www.domain.UserDto;

import java.util.List;

public interface UserDao {
    int count() throws Exception;
    List<UserDto> selectAll() throws Exception;
    void deleteAll() throws Exception;
    int insert(UserDto userDto) throws Exception;   // Create - C
    UserDto select(String id) throws Exception; // Read - R
    int modify(UserDto userDto) throws Exception;   // Update - U
    int remove(String id) throws Exception; // Delete - D
}