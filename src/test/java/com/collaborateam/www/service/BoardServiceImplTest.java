package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
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
    public void init() {
        boardDto1 = new BoardDto("title1", "content1", "writer1");
        boardDto2 = new BoardDto("title2", "content2", "writer2");
    }

    @Test
    public void getCountTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        List<BoardDto> boardDtoList = boardService.getList();
        assertEquals(0, boardDtoList.size());

        assertEquals(1, boardService.write(boardDto1));
        boardDtoList = boardService.getList();
        assertEquals(1, boardDtoList.size());

        assertEquals(1, boardService.write(boardDto2));
        boardDtoList = boardService.getList();
        assertEquals(2, boardDtoList.size());
    }

    @Test
    public void removeAllPostsTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());

        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());
    }

    @Test
    public void writeTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        List<BoardDto> boardDtoList = boardService.getList();

        Integer bno = boardDtoList.get(0).getBno();
        boardDto1.setBno(bno);

        BoardDto boardDto3 = boardService.read(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());

        bno = boardService.getList().get(0).getBno();
        boardDto2.setBno(bno);

        BoardDto boardDto4 = boardService.read(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void modifyTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        Integer bno = boardService.getList().get(0).getBno();
        String writer = boardService.getList().get(0).getWriter();

        boardDto1.setBno(bno);
        boardDto1.setTitle("title10");
        boardDto1.setContent("content10");
        boardDto1.setWriter(writer);

        assertEquals(1, boardService.modify(boardDto1));

        BoardDto boardDto3 = boardService.read(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());

        bno = boardService.getList().get(1).getBno();
        writer = boardService.getList().get(1).getWriter();

        boardDto2.setBno(bno);
        boardDto2.setTitle("title20");
        boardDto2.setContent("content20");
        boardDto2.setWriter(writer);

        assertEquals(1, boardService.modify(boardDto2));

        BoardDto boardDto4 = boardService.read(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void removeTest() throws Exception {
        boardService.removeAllPosts();
        assertEquals(0, boardService.getCount());

        assertEquals(1, boardService.write(boardDto1));
        assertEquals(1, boardService.getCount());

        assertEquals(1, boardService.write(boardDto2));
        assertEquals(2, boardService.getCount());

        Integer bno1 = boardService.getList().get(0).getBno();
        String writer1 = boardService.getList().get(0).getWriter();
        Integer bno2 = boardService.getList().get(1).getBno();
        String writer2 = boardService.getList().get(1).getWriter();

        assertEquals(0, boardService.remove(bno1 + 111, writer1));
        assertEquals(0, boardService.remove(bno1, writer1 + "111"));
        assertEquals(0, boardService.remove(bno1 + 111, writer1 + "111"));
        assertEquals(1, boardService.remove(bno1, writer1));
        assertEquals(1, boardService.getCount());
        assertNull(boardService.read(bno1));

        assertEquals(0, boardService.remove(bno2 + 222, writer2));
        assertEquals(0, boardService.remove(bno2, writer2 + "222"));
        assertEquals(0, boardService.remove(bno2 + 222, writer2 + "222"));
        assertEquals(1, boardService.remove(bno2, writer2));
        assertEquals(0, boardService.getCount());
        assertNull(boardService.read(bno2));
    }

    @Test
    public void getPageTest() throws Exception {
        boardService.removeAllPosts();

        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto3 = new BoardDto("title"+i, "content"+i, "writer"+i);
            assertEquals(1, boardService.write(boardDto3));
        }

        int offset = 0;
        int pageSize = 3;

        List<BoardDto> boardDtoList = boardService.getPage(offset, pageSize);
        assertEquals("title10", boardDtoList.get(0).getTitle());
        assertEquals("title9", boardDtoList.get(1).getTitle());
        assertEquals("title8", boardDtoList.get(2).getTitle());

        pageSize = 1;

        boardDtoList = boardService.getPage(offset, pageSize);
        assertEquals("title10", boardDtoList.get(0).getTitle());

        offset = 7;
        pageSize = 3;

        boardDtoList = boardService.getPage(offset, pageSize);
        assertEquals("title3", boardDtoList.get(0).getTitle());
        assertEquals("title2", boardDtoList.get(1).getTitle());
        assertEquals("title1", boardDtoList.get(2).getTitle());
    }
}