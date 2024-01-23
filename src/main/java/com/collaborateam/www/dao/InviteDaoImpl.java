package com.collaborateam.www.dao;

import com.collaborateam.www.domain.InviteDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InviteDaoImpl implements InviteDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.InviteMapper.";

    @Override
    public int insert(InviteDto inviteDto) throws Exception {
        return session.insert(namespace+"insert", inviteDto);
    }

    @Override
    public InviteDto select(Integer ino) throws Exception {
        return session.selectOne(namespace+"select", ino);
    }

    @Override
    public int delete(Integer ino) throws Exception {
        return session.delete(namespace+"delete", ino);
    }

    @Override
    public List<InviteDto> selectByUser(String id) throws Exception {
        return session.selectList(namespace+"selectByUser", id);
    }
}