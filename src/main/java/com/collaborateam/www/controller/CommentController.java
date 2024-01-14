package com.collaborateam.www.controller;

import com.collaborateam.www.domain.CommentDto;
import com.collaborateam.www.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list;
        try {
            list = commentService.getList(bno);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "commenter1";
        commentDto.setBno(bno);
        commentDto.setCommenter(commenter);

        try {
            int rowCnt = commentService.write(commentDto);

            if(rowCnt != 1)
                throw new Exception("Comment write failed");

            return new ResponseEntity<>("COMMENT_WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("COMMENT_WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto) {
        //        String commenter = (String)session.getAttribute("id");
        String commenter = "commenter1";
        commentDto.setCno(cno);
        commentDto.setCommenter(commenter);

        try {
            int rowCnt = commentService.modify(commentDto);

            if(rowCnt != 1)
                throw new Exception("Comment modify failed");

            return new ResponseEntity<>("COMMENT_MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("COMMENT_MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "commenter1";

        try {
            int rowCnt = commentService.remove(cno, bno, commenter);

            if(rowCnt != 1)
                throw new Exception("Comment delete failed");

            return new ResponseEntity<>("COMMENT_DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("COMMENT_DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}