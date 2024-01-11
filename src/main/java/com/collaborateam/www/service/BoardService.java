package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.SearchCondition;

import java.util.List;

public interface BoardService {
    int getCount() throws Exception;
    List<BoardDto> getList() throws Exception;
    void removeAllBoards() throws Exception;
    int write(BoardDto boardDto) throws Exception;
    BoardDto read(Integer bno) throws Exception;
    int modify(BoardDto boardDto) throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    List<BoardDto> getPage(Integer offset, Integer pageSize) throws Exception;
    List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
    int getSearchResultCnt(SearchCondition sc) throws Exception;
}