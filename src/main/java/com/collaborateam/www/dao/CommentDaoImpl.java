package com.collaborateam.www.dao;

import com.collaborateam.www.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.collaborateam.www.dao.CommentMapper.";

    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(namespace+"count", bno);
    }

    @Override
    public List<CommentDto> selectAll(Integer bno) throws Exception {
        return session.selectList(namespace+"selectAll", bno);
    }

    @Override
    public int removeAll(Integer bno) {
        return session.delete(namespace+"removeAll", bno);
    }

    @Override
    public int insert(CommentDto commentDto) throws Exception {
        return session.insert(namespace+"insert", commentDto);
    }

    @Override
    public CommentDto select(Integer cno) throws Exception {
        return session.selectOne(namespace + "select", cno);
    }

    @Override
    public int modify(CommentDto commentDto) throws Exception {
        return session.update(namespace+"modify", commentDto);
    }

    @Override
    public int remove(Integer cno, String commenter) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("cno", cno);
        map.put("commenter", commenter);
        return session.delete(namespace+"remove", map);
    }
}