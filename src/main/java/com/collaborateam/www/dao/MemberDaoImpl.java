package com.collaborateam.www.dao;

import com.collaborateam.www.domain.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.MemberMapper.";
    @Override
    public int count(Integer tno) throws Exception {
        return session.selectOne(namespace+"count", tno);
    }

    @Override
    public List<MemberDto> selectAll(Integer tno) throws Exception {
        return session.selectList(namespace+"selectAll", tno);
    }

    @Override
    public int deleteAll(Integer tno) {
        return session.delete(namespace+"deleteAll", tno);
    }

    @Override
    public int insert(MemberDto memberDto) throws Exception {
        return session.insert(namespace+"insert", memberDto);
    }

    @Override
    public MemberDto select(Integer mno) throws Exception {
        return session.selectOne(namespace + "select", mno);
    }

    @Override
    public int update(MemberDto memberDto) throws Exception {
        return session.update(namespace+"update", memberDto);
    }

    @Override
    public int delete(Integer mno, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("mno", mno);
        map.put("id", id);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public boolean exist(Integer tno, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("tno", tno);
        map.put("id", id);
        Integer cnt = session.selectOne(namespace+"exist", map);
        return cnt > 0;
    }
}