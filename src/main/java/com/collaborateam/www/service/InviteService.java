package com.collaborateam.www.service;

import com.collaborateam.www.domain.InviteDto;

import java.util.List;

public interface InviteService {
    int create(InviteDto inviteDto) throws Exception;
    InviteDto read(Integer ino) throws Exception;
    int remove(Integer ino) throws Exception;
    List<InviteDto> getUserInvitation(String id) throws Exception;
}