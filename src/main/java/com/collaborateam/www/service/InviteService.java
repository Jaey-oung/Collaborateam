package com.collaborateam.www.service;

import com.collaborateam.www.domain.InviteDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InviteService {
    int getCount() throws Exception;
    List<InviteDto> getList() throws Exception;
    void deleteAll() throws Exception;
    @Transactional(rollbackFor = Exception.class)
    int create(InviteDto inviteDto) throws Exception;
    InviteDto read(Integer ino) throws Exception;
    int update(InviteDto inviteDto) throws Exception;
    int delete(Integer ino, String id) throws Exception;
    List<InviteDto> getUserInvite(String id) throws Exception;
}