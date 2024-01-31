package com.collaborateam.www.dao;

import com.collaborateam.www.domain.InviteDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InviteDaoImpl implements InviteDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.InviteMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<InviteDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public void removeAll() throws Exception {
        session.delete(namespace+"removeAll");
    }

    @Override
    public int insert(InviteDto inviteDto) throws Exception {
        return session.insert(namespace+"insert", inviteDto);
    }

    @Override
    public InviteDto select(Integer ino) throws Exception {
        return session.selectOne(namespace + "select", ino);
    }

    @Override
    public int modify(InviteDto inviteDto) throws Exception {
        return session.update(namespace+"modify", inviteDto);
    }

    @Override
    public int remove(Integer ino, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("ino", ino);
        map.put("id", id);
        return session.delete(namespace+"remove", map);
    }

    @Override
    public List<InviteDto> selectUserInvite(String id) throws Exception {
        return session.selectList(namespace+"selectUserInvite", id);
    }

    @Override
    public boolean isUserExist(Integer tno, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tno", tno);
        map.put("id", id);
        return (Integer)session.selectOne(namespace+"isUserExist", map) > 0;
    }
}