package com.collaborateam.www.dao;

import com.collaborateam.www.domain.InviteDto;

import java.util.List;

public interface InviteDao {
    int count() throws Exception;
    List<InviteDto> selectAll() throws Exception;
    void removeAll() throws Exception;
    int insert(InviteDto inviteDto) throws Exception;
    InviteDto select(Integer ino) throws Exception;
    int modify(InviteDto inviteDto) throws Exception;
    int remove(Integer ino, String id) throws Exception;
    List<InviteDto> selectUserInvite(String id) throws Exception;
    boolean isUserExist(Integer tno, String id) throws Exception;
}