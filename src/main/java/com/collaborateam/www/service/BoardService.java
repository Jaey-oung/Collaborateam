package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;

import java.util.List;

public interface BoardService {
    int getCount() throws Exception;
    List<BoardDto> getList() throws Exception;
    void deleteAll() throws Exception;
    int create(BoardDto boardDto) throws Exception;
    BoardDto read(Integer bno) throws Exception;
    int update(BoardDto boardDto) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    List<BoardDto> getBoardPage(BoardListCondition blc) throws Exception;
    int getBoardCnt(BoardListCondition blc) throws Exception;
}