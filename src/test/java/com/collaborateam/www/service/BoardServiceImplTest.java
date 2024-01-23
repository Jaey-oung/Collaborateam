//package com.collaborateam.www.service;
//
//import com.collaborateam.www.domain.BoardDto;
//import com.collaborateam.www.domain.BoardListCondition;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class BoardServiceImplTest {
//    @Autowired
//    BoardService boardService;
//    BoardDto boardDto1;
//    BoardDto boardDto2;
//
//    @Before
//    public void init() throws Exception {
//        boardService.removeAllBoards();
//        boardDto1 = new BoardDto("IT", "WD", "title1", "content1", "writer1");
//        boardDto2 = new BoardDto("IT", "SD", "title2", "content2", "writer2");
//    }
//
//    @Test
//    public void insertData() throws Exception {
//        for (int i = 1; i <= 50; i++) {
//            BoardDto boardDto = new BoardDto("IT", "WD", "title" + i, "content" + i, "user1");
//            boardService.write(boardDto);
//            BoardDto boardDto2 = new BoardDto("IT", "SD", "title" + i, "content" + i, "user1");
//            boardService.write(boardDto2);
//        }
//    }
//
//    @Test
//    public void getCountTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//    }
//
//    @Test
//    public void getListTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        List<BoardDto> list = boardService.getList();
//        assertEquals(0, list.size());
//
//        assertEquals(1, boardService.write(boardDto1));
//        list = boardService.getList();
//        assertEquals(1, list.size());
//
//        assertEquals(1, boardService.write(boardDto2));
//        list = boardService.getList();
//        assertEquals(2, list.size());
//    }
//
//    @Test
//    public void removeAllBoardsTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//
//        boardService.removeAllBoards();
//        assertEquals(0, boardService.getCount());
//    }
//
//    @Test
//    public void writeTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//    }
//
//    @Test
//    public void readTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        List<BoardDto> list = boardService.getList();
//
//        Integer bno = list.get(0).getBno();
//        boardDto1.setBno(bno);
//
//        BoardDto boardDto3 = boardService.read(bno);
//        assertEquals(boardDto1, boardDto3);
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//
//        bno = boardService.getList().get(0).getBno();
//        boardDto2.setBno(bno);
//
//        BoardDto boardDto4 = boardService.read(bno);
//        assertEquals(boardDto2, boardDto4);
//    }
//
//    @Test
//    public void modifyTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        Integer bno = boardService.getList().get(0).getBno();
//        String writer = boardService.getList().get(0).getWriter();
//
//        boardDto1.setBno(bno);
//        boardDto1.setField("Finance");
//        boardDto1.setSpecialization("RM");
//        boardDto1.setTitle("title10");
//        boardDto1.setContent("content10");
//        boardDto1.setWriter(writer);
//
//        assertEquals(1, boardService.modify(boardDto1));
//
//        BoardDto boardDto3 = boardService.read(bno);
//        assertEquals(boardDto1, boardDto3);
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//
//        bno = boardService.getList().get(0).getBno();
//        writer = boardService.getList().get(0).getWriter();
//
//        boardDto2.setBno(bno);
//        boardDto2.setField("Finance");
//        boardDto2.setSpecialization("FA");
//        boardDto2.setTitle("title20");
//        boardDto2.setContent("content20");
//        boardDto2.setWriter(writer);
//
//        assertEquals(1, boardService.modify(boardDto2));
//
//        BoardDto boardDto4 = boardService.read(bno);
//        assertEquals(boardDto2, boardDto4);
//    }
//
//    @Test
//    public void removeTest() throws Exception {
//        assertEquals(0, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto1));
//        assertEquals(1, boardService.getCount());
//
//        assertEquals(1, boardService.write(boardDto2));
//        assertEquals(2, boardService.getCount());
//
//        Integer bno1 = boardService.getList().get(1).getBno();
//        String user1 = boardService.getList().get(1).getWriter();
//        Integer bno2 = boardService.getList().get(0).getBno();
//        String user2 = boardService.getList().get(0).getWriter();
//
//        assertEquals(0, boardService.remove(bno1 + 111, user1));
//        assertEquals(0, boardService.remove(bno1, user1 + "111"));
//        assertEquals(0, boardService.remove(bno1 + 111, user1 + "111"));
//        assertEquals(1, boardService.remove(bno1, user1));
//        assertEquals(1, boardService.getCount());
//        assertNull(boardService.read(bno1));
//
//        assertEquals(0, boardService.remove(bno2 + 222, user2));
//        assertEquals(0, boardService.remove(bno2, user2 + "222"));
//        assertEquals(0, boardService.remove(bno2 + 222, user2 + "222"));
//        assertEquals(1, boardService.remove(bno2, user2));
//        assertEquals(0, boardService.getCount());
//        assertNull(boardService.read(bno2));
//    }
//
//    @Test
//    public void getPageTest() throws Exception {
//        for (int i = 1; i <= 10; i++) {
//            BoardDto boardDto3 = new BoardDto("IT", "WD", "title"+i, "content"+i, "writer"+i);
//            assertEquals(1, boardService.write(boardDto3));
//        }
//
//        int offset = 0;
//        int pageSize = 3;
//
//        List<BoardDto> list = boardService.getPage(offset, pageSize);
//        assertEquals("title10", list.get(0).getTitle());
//        assertEquals("title9", list.get(1).getTitle());
//        assertEquals("title8", list.get(2).getTitle());
//
//        pageSize = 1;
//
//        list = boardService.getPage(offset, pageSize);
//        assertEquals("title10", list.get(0).getTitle());
//
//        offset = 7;
//        pageSize = 3;
//
//        list = boardService.getPage(offset, pageSize);
//        assertEquals("title3", list.get(0).getTitle());
//        assertEquals("title2", list.get(1).getTitle());
//        assertEquals("title1", list.get(2).getTitle());
//    }
//
//    @Test
//    public void getSearchResultPageTest() throws Exception {
//        String[] titles = {"title1", "title2"};
//        String[] contents = {"content1", "content2"};
//        String[] writers = {"writer1", "writer2"};
//
//        for (String title : titles) {
//            for (String content : contents) {
//                for (String writer : writers) {
//                    BoardDto boardDto = new BoardDto("IT", "WD", title, content, writer);
//                    boardService.write(boardDto);
//                }   // title1, content1, writer1 / title1, content1, writer2
//            }       // title1, content2, writer1 / title1, content2, writer2
//        }           // title2, content1, writer1 / title2, content1, writer2
//        // title2, content2, writer1 / title2, content2, writer2
//
//        BoardListCondition blc = new BoardListCondition(1, 5, "IT", "WD", "T", "title");
//        List<BoardDto> list = boardService.getSearchResultPage(blc);    // LIMIT #{offset}, #{pageSize}
//        assertEquals(5, list.size());                  // If searchResultPage exceeds pageSize, it returns pageSize
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "test");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(0, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(8, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title2");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(4, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(8, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer2");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(4, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "title");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(8, list.size());
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "content2");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(4, list.size());
//
//        blc = new BoardListCondition(1, 10, "TEST", "TEST", "A", "content2");
//        list = boardService.getSearchResultPage(blc);
//        assertEquals(0, list.size());
//    }
//
//    @Test
//    public void getSearchResultCntTest() throws Exception {
//        boardService.removeAllBoards();
//
//        String[] titles = {"title1", "title2"};
//        String[] contents = {"content1", "content2"};
//        String[] writers = {"writer1", "writer2"};
//
//        for (String title : titles) {
//            for (String content : contents) {
//                for (String writer : writers) {
//                    BoardDto boardDto = new BoardDto("IT", "WD", title, content, writer);
//                    boardService.write(boardDto);
//                }   // title1, content1, writer1 / title1, content1, writer2
//            }       // title1, content2, writer1 / title1, content2, writer2
//        }           // title2, content1, writer1 / title2, content1, writer2
//        // title2, content2, writer1 / title2, content2, writer2
//
//        BoardListCondition blc = new BoardListCondition(1, 5, "IT", "WD", "T", "title");
//        int cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(8, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "test");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(0, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(8, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "T", "title2");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(4, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(8, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "W", "writer2");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(4, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "title");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(8, cnt);
//
//        blc = new BoardListCondition(1, 10, "IT", "WD", "TC", "content2");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(4, cnt);
//
//        blc = new BoardListCondition(1, 10, "TEST", "TEST", "A", "content2");
//        cnt = boardService.getSearchResultCnt(blc);
//        assertEquals(0, cnt);
//    }
//}