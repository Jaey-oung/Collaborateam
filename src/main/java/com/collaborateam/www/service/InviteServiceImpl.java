package com.collaborateam.www.service;

import com.collaborateam.www.dao.InviteDao;
import com.collaborateam.www.domain.InviteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InviteServiceImpl implements InviteService {
    @Autowired
    InviteDao inviteDao;

    @Override
    public int getCount() throws Exception {
        return inviteDao.count();
    }

    @Override
    public List<InviteDto> getList() throws Exception {
        return inviteDao.selectAll();
    }

    @Override
    public void deleteAll() throws Exception {
        inviteDao.removeAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(InviteDto inviteDto) throws Exception {
        return inviteDao.isUserExist(inviteDto.getTno(), inviteDto.getId()) ? 0 : inviteDao.insert(inviteDto);
    }

    @Override
    public InviteDto read(Integer ino) throws Exception {
        return inviteDao.select(ino);
    }

    @Override
    public int update(InviteDto inviteDto) throws Exception {
        return inviteDao.modify(inviteDto);
    }

    @Override
    public int delete(Integer ino, String user_id) throws Exception {
        return inviteDao.remove(ino, user_id);
    }

    @Override
    public List<InviteDto> getUserInvite(String id) throws Exception {
        return inviteDao.selectUserInvite(id);
    }
}