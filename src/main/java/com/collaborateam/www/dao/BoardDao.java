package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;

import java.util.List;

public interface BoardDao {
    int count() throws Exception;
    List<BoardDto> selectAll() throws Exception;
    void removeAll() throws Exception;
    int insert(BoardDto boardDto) throws Exception;
    BoardDto select(Integer bno) throws Exception;
    int modify(BoardDto boardDto) throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    List<BoardDto> boardPage(BoardListCondition blc) throws Exception;
    int boardCnt(BoardListCondition blc) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    int updateCommentCnt(Integer bno, int cnt);
}