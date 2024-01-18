package com.collaborateam.www.service;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.CommentDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceImplTest {
    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;
    Integer bno;
    BoardDto boardDto1;
    CommentDto commentDto1;
    CommentDto commentDto2;

    @Before
    public void init() throws Exception {
        boardService.removeAllBoards();

        boardDto1 = new BoardDto("IT", "Web Development", "title1", "content1", "writer1");
        boardService.write(boardDto1);

        bno = boardService.getList().get(0).getBno();
        commentService.removeAllComments(bno);

        commentDto1 = new CommentDto(bno, 0, "comment1", "commenter1");
        commentDto2 = new CommentDto(bno, 0, "comment2", "commenter2");
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        List<CommentDto> list = commentService.getList(bno);
        assertEquals(0, list.size());

        assertEquals(1, commentService.write(commentDto1));
        list = commentService.getList(bno);
        assertEquals(1, list.size());

        assertEquals(1, commentService.write(commentDto2));
        list = commentService.getList(bno);
        assertEquals(2, list.size());
    }

    @Test
    public void removeAllCommentsTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));

        commentService.removeAllComments(bno);
        assertEquals(0, commentService.getCount(bno));
    }

    @Test
    public void writeTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        List<CommentDto> list = commentService.getList(bno);

        Integer cno = list.get(0).getCno();
        commentDto1.setCno(cno);

        CommentDto commentDto3 = commentService.read(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));

        list = commentService.getList(bno);

        cno = list.get(1).getCno();
        commentDto2.setCno(cno);

        CommentDto commentDto4 = commentService.read(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void modifyTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        Integer cno = commentService.getList(bno).get(0).getCno();
        String commenter = commentService.getList(bno).get(0).getCommenter();

        commentDto1.setCno(cno);
        commentDto1.setComment("comment10");
        commentDto1.setCommenter(commenter);

        assertEquals(1, commentService.modify(commentDto1));

        CommentDto commentDto3 = commentService.read(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));

        cno = commentService.getList(bno).get(1).getCno();
        commenter = commentService.getList(bno).get(1).getCommenter();

        commentDto2.setCno(cno);
        commentDto2.setComment("comment20");
        commentDto2.setCommenter(commenter);

        assertEquals(1, commentService.modify(commentDto2));

        CommentDto commentDto4 = commentService.read(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void removeTest() throws Exception {
        assertEquals(0, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto1));
        assertEquals(1, commentService.getCount(bno));

        assertEquals(1, commentService.write(commentDto2));
        assertEquals(2, commentService.getCount(bno));

        Integer cno1 = commentService.getList(bno).get(0).getCno();
        String commenter1 = commentService.getList(bno).get(0).getCommenter();
        Integer cno2 = commentService.getList(bno).get(1).getCno();
        String commenter2 = commentService.getList(bno).get(1).getCommenter();

        assertEquals(0, commentService.remove(cno1 + 111, bno, commenter1));
        assertEquals(0, commentService.remove(cno1, bno, commenter1 + "111"));
        assertEquals(0, commentService.remove(cno1 + 111, bno, commenter1 + "111"));
        assertEquals(1, commentService.remove(cno1, bno, commenter1));
        assertEquals(1, commentService.getCount(bno));
        assertNull(commentService.read(cno1));

        assertEquals(0, commentService.remove(cno2 + 222, bno, commenter2));
        assertEquals(0, commentService.remove(cno2, bno, commenter2 + "222"));
        assertEquals(0, commentService.remove(cno2 + 222, bno, commenter2 + "222"));
        assertEquals(1, commentService.remove(cno2, bno, commenter2));
        assertEquals(0, commentService.getCount(bno));
        assertNull(commentService.read(cno2));
    }
}