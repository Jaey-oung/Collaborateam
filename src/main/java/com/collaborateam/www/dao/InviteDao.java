package com.collaborateam.www.dao;

import com.collaborateam.www.domain.InviteDto;

import java.util.List;

public interface InviteDao {
    int insert(InviteDto inviteDto) throws Exception;
    InviteDto select(Integer ino) throws Exception;
    int delete(Integer ino) throws Exception;
    List<InviteDto> selectByUser(String id) throws Exception;
}