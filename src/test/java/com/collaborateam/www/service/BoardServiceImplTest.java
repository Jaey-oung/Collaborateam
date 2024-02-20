package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardServiceImplTest {
    @Autowired
    BoardService boardService;
    BoardDto boardDto1;
    BoardDto boardDto2;

    @Before
    public void init() throws Exception {
        boardDto1 = new BoardDto("", "", "title1", "content1", "writer1");
        boardDto2 = new BoardDto("", "", "title2", "content2", "writer2");

        boardService.deleteAll();
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, boardService.getCount());

        List<BoardDto> list = boardService.getList();
        assertEquals(0, list.size());

        assertEquals(1, boardService.create(boardDto1));
        list = boardService.getList();
        assertEquals(1, list.size());

        assertEquals(1, boardService.create(boardDto2));
        list = boardService.getList();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());

        boardService.deleteAll();
        assertEquals(0, boardService.getCount());
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        Integer bno = boardService.getList().get(0).getBno();
        boardDto1.setBno(bno);

        BoardDto boardDto3 = boardService.read(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());

        bno = boardService.getList().get(0).getBno();
        boardDto2.setBno(bno);

        BoardDto boardDto4 = boardService.read(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        Integer bno = boardService.getList().get(0).getBno();
        String writer = boardService.getList().get(0).getWriter();

        boardDto1.setBno(bno);
        boardDto1.setField("FIN");
        boardDto1.setSpec("RM");
        boardDto1.setTitle("title10");
        boardDto1.setContent("content10");
        boardDto1.setWriter(writer);

        assertEquals(1, boardService.update(boardDto1));

        BoardDto boardDto3 = boardService.read(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());

        bno = boardService.getList().get(0).getBno();
        writer = boardService.getList().get(0).getWriter();

        boardDto2.setBno(bno);
        boardDto2.setField("FIN");
        boardDto2.setSpec("FA");
        boardDto2.setTitle("title20");
        boardDto2.setContent("content20");
        boardDto2.setWriter(writer);

        assertEquals(1, boardService.update(boardDto2));

        BoardDto boardDto4 = boardService.read(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.create(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.create(boardDto2));
        assertEquals(2, boardService.getCount());

        Integer bno = boardService.getList().get(1).getBno();
        String writer = boardService.getList().get(1).getWriter();

        assertEquals(0, boardService.delete(bno + 111, writer));
        assertEquals(0, boardService.delete(bno, writer + "111"));
        assertEquals(0, boardService.delete(bno + 111, writer + "111"));
        assertEquals(1, boardService.delete(bno, writer));
        assertEquals(1, boardService.getCount());
        assertNull(boardService.read(bno));

        bno = boardService.getList().get(0).getBno();
        writer = boardService.getList().get(0).getWriter();

        assertEquals(0, boardService.delete(bno + 222, writer));
        assertEquals(0, boardService.delete(bno, writer + "222"));
        assertEquals(0, boardService.delete(bno + 222, writer + "222"));
        assertEquals(1, boardService.delete(bno, writer));
        assertEquals(0, boardService.getCount());
        assertNull(boardService.read(bno));
    }

    @Test
    public void getBoardPageTest() throws Exception {
        BoardDto[] boardDtos = new BoardDto[] {
                new BoardDto("A", "A", "title1", "content1", "writer1"),
                new BoardDto("IT", "A", "title2", "content2", "writer2"),
                new BoardDto("IT", "WD", "title3", "content3", "writer3"),
                new BoardDto("IT", "SD", "title4", "content4", "writer4"),
                new BoardDto("FIN", "A", "title5", "content5", "writer5"),
                new BoardDto("FIN", "RM", "title6", "content6", "writer6"),
                new BoardDto("FIN", "FA", "title7", "content7", "writer7")
        };

        for (BoardDto boardDto : boardDtos) {
            boardService.create(boardDto);
        }

        // All field

        BoardListCondition blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "");
        List<BoardDto> list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "T", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "W", "writer", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "TC", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "TC", "content", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        // IT field

        blc = new BoardListCondition(1, 10, "IT", "A", "A", "all", "");
        list = boardService.getBoardPage(blc);
        assertEquals(3, list.size());

        blc = new BoardListCondition(1, 10, "IT", "A", "T", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(3, list.size());

        blc = new BoardListCondition(1, 10, "IT", "A", "W", "writer", "");
        list = boardService.getBoardPage(blc);
        assertEquals(3, list.size());

        blc = new BoardListCondition(1, 10, "IT", "A", "TC", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(3, list.size());

        blc = new BoardListCondition(1, 10, "IT", "A", "TC", "content", "");
        list = boardService.getBoardPage(blc);
        assertEquals(3, list.size());

        // IT field + WD spec

        blc = new BoardListCondition(1, 10, "IT", "WD", "A", "all", "");
        list = boardService.getBoardPage(blc);
        assertEquals(1, list.size());

        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(1, list.size());

        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer", "");
        list = boardService.getBoardPage(blc);
        assertEquals(1, list.size());

        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(1, list.size());

        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "content", "");
        list = boardService.getBoardPage(blc);
        assertEquals(1, list.size());
    }

    @Test
    public void getBoardCntTest() throws Exception {
        BoardDto[] boardDtos = new BoardDto[] {
                new BoardDto("A", "A", "title1", "content1", "writer1"),
                new BoardDto("IT", "A", "title2", "content2", "writer2"),
                new BoardDto("IT", "WD", "title3", "content3", "writer3"),
                new BoardDto("IT", "SD", "title4", "content4", "writer4"),
                new BoardDto("FIN", "A", "title5", "content5", "writer5"),
                new BoardDto("FIN", "RM", "title6", "content6", "writer6"),
                new BoardDto("FIN", "FA", "title7", "content7", "writer7")
        };

        for (BoardDto boardDto : boardDtos) {
            boardService.create(boardDto);
        }

        // All field

        BoardListCondition blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "");
        int rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "T", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "W", "writer", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "TC", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "TC", "content", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        // IT field

        blc = new BoardListCondition(1, 10, "IT", "A", "A", "all", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(3, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "A", "T", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(3, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "A", "W", "writer", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(3, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "A", "TC", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(3, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "A", "TC", "content", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(3, rowCnt);

        // IT field + WD spec

        blc = new BoardListCondition(1, 10, "IT", "WD", "A", "all", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(1, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(1, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(1, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(1, rowCnt);

        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "content", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(1, rowCnt);
    }
}