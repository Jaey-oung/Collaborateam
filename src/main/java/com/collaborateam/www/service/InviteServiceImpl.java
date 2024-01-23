package com.collaborateam.www.service;

import com.collaborateam.www.dao.InviteDao;
import com.collaborateam.www.domain.InviteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteServiceImpl implements InviteService {
    @Autowired
    InviteDao inviteDao;

    @Override
    public int create(InviteDto inviteDto) throws Exception {
        return inviteDao.insert(inviteDto);
    }

    @Override
    public InviteDto read(Integer ino) throws Exception {
        return inviteDao.select(ino);
    }

    @Override
    public int remove(Integer ino) throws Exception {
        return inviteDao.delete(ino);
    }

    @Override
    public List<InviteDto> getUserInvitation(String id) throws Exception {
        return inviteDao.selectByUser(id);
    }
}