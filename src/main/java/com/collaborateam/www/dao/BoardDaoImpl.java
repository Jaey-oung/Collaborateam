package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.BoardMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public void deleteAll() throws Exception {
        session.delete(namespace+"deleteAll");
    }

    @Override
    public int insert(BoardDto boardDto) throws Exception {
        return session.insert(namespace+"insert", boardDto);
    }   // Create - C

    @Override
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select", bno);
    }   // Read - R

    @Override
    public int update(BoardDto boardDto) throws Exception {
        return session.update(namespace+"update", boardDto);
    }   // Update - U

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace+"delete", map);
    }   // Delete - D

    @Override
    public List<BoardDto> selectPage(Integer offset, Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    }
}