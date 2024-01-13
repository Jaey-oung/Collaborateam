package com.collaborateam.www.dao;

import com.collaborateam.www.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int count(Integer bno) throws Exception;
    List<CommentDto> selectAll(Integer bno) throws Exception;
    int deleteAll(Integer bno);
    int insert(CommentDto commentDto) throws Exception;
    CommentDto select(Integer cno) throws Exception;
    int update(CommentDto commentDto) throws Exception;
    int delete(Integer cno, String commenter) throws Exception;
}