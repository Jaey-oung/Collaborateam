package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;

import java.util.List;

public interface BoardDao {
    int count() throws Exception;
    List<BoardDto> selectAll() throws Exception;
    void deleteAll() throws Exception;
    int insert(BoardDto boardDto) throws Exception;  // Create - C
    BoardDto select(Integer bno) throws Exception;  // Read - R
    int update(BoardDto boardDto) throws Exception;  // Update - U
    int delete(Integer bno, String writer) throws Exception;    // Delete - D
}