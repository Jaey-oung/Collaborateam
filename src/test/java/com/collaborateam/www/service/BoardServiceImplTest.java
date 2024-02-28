package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
import com.collaborateam.www.domain.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardServiceImplTest {
    @Autowired
    UserService userService;
    UserDto userDto1;
    UserDto userDto2;
    Calendar birth;

    @Autowired
    BoardService boardService;
    BoardDto boardDto1;
    BoardDto boardDto2;

    @Before
    public void init() throws Exception {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19);

        userDto1 = new UserDto("id1", "pwd1", "email1", "id1", birth.getTime());
        userDto2 = new UserDto("id2", "pwd2", "email2", "id2", birth.getTime());
        boardDto1 = new BoardDto("", "", "title1", "content1", userDto1.getId());
        boardDto2 = new BoardDto("", "", "title2", "content2", userDto2.getId());

        userService.deleteAll();
        userService.create(userDto1);
        userService.create(userDto2);

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
                new BoardDto("A", "A", "title1", "content1", userDto1.getId()),
                new BoardDto("IT", "A", "title2", "content2", userDto1.getId()),
                new BoardDto("IT", "WD", "title3", "content3", userDto1.getId()),
                new BoardDto("IT", "SD", "title4", "content4", userDto2.getId()),
                new BoardDto("FIN", "A", "title5", "content5", userDto2.getId()),
                new BoardDto("FIN", "RM", "title6", "content6", userDto2.getId()),
                new BoardDto("FIN", "FA", "title7", "content7", userDto2.getId())
        };

        for (BoardDto boardDto : boardDtos) {
            boardService.create(boardDto);
        }

        // All field

        BoardListCondition blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "A");
        List<BoardDto> list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "T", "title", "");
        list = boardService.getBoardPage(blc);
        assertEquals(7, list.size());

        blc = new BoardListCondition(1, 10, "A", "A", "W", "id", "");
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

        blc = new BoardListCondition(1, 10, "IT", "A", "W", "id", "");
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

        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "id", "");
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
                new BoardDto("A", "A", "title1", "content1", userDto1.getId()),
                new BoardDto("IT", "A", "title2", "content2", userDto1.getId()),
                new BoardDto("IT", "WD", "title3", "content3", userDto1.getId()),
                new BoardDto("IT", "SD", "title4", "content4", userDto2.getId()),
                new BoardDto("FIN", "A", "title5", "content5", userDto2.getId()),
                new BoardDto("FIN", "RM", "title6", "content6", userDto2.getId()),
                new BoardDto("FIN", "FA", "title7", "content7", userDto2.getId())
        };

        for (BoardDto boardDto : boardDtos) {
            boardService.create(boardDto);
        }

        // All field

        BoardListCondition blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "A");
        int rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "A", "all", "A");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "T", "title", "");
        rowCnt = boardService.getBoardCnt(blc);
        assertEquals(7, rowCnt);

        blc = new BoardListCondition(1, 10, "A", "A", "W", "id", "");
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

        blc = new BoardListCondition(1, 10, "IT", "A", "W", "id", "");
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

        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "id", "");
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