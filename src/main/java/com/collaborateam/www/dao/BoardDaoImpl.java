package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
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

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public void removeAll() throws Exception {
        session.delete(namespace+"removeAll");
    }

    @Override
    public int insert(BoardDto boardDto) throws Exception {
        return session.insert(namespace+"insert", boardDto);
    }

    @Override
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    }

    @Override
    public int modify(BoardDto boardDto) throws Exception {
        return session.update(namespace+"modify", boardDto);
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace+"remove", map);
    }

    @Override
    public List<BoardDto> boardPage(BoardListCondition blc) throws Exception {
        return session.selectList(namespace+"boardPage", blc);
    }

    @Override
    public int boardCnt(BoardListCondition blc) throws Exception {
        return session.selectOne(namespace+"boardCnt", blc);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    }

    @Override
    public int updateCommentCnt(Integer bno, int cnt) {
        Map<String, Object> map = new HashMap<>();
        map.put("bno", bno);
        map.put("cnt", cnt);
        return session.update(namespace+"updateCommentCnt", map);
    }
}