package com.collaborateam.www.dao;

import com.collaborateam.www.domain.UserDto;

public interface UserDao {
    int countUser() throws Exception;
    void deleteAllUser() throws Exception;
    int insertUser(UserDto userDto) throws Exception; // Create - C
//    UserDto selectUser(String id) throws Exception; // Read - C
//    int updateUser(UserDto userDto) throws Exception; // Update - U
//    int deleteUser(String id) throws Exception; // Delete - D
}
