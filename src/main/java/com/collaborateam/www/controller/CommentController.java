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
    public ResponseEntity<String> create(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
        String commenter = (String)session.getAttribute("id");
        commentDto.setBno(bno);
        commentDto.setCommenter(commenter);

        try {
            int rowCnt = commentService.create(commentDto);

            if(rowCnt != 1)
                throw new Exception("Comment create failed");

            return new ResponseEntity<>("Successfully created the comment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create the comment", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> update(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        String commenter = (String)session.getAttribute("id");
        commentDto.setCno(cno);
        commentDto.setCommenter(commenter);

        try {
            int rowCnt = commentService.update(commentDto);

            if(rowCnt != 1)
                throw new Exception("Comment update failed");

            return new ResponseEntity<>("Successfully updated the comment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update the comment", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> delete(@PathVariable Integer cno, Integer bno, HttpSession session) {
        String commenter = (String)session.getAttribute("id");

        try {
            int rowCnt = commentService.delete(cno, bno, commenter);

            if(rowCnt != 1)
                throw new Exception("Comment delete failed");

            return new ResponseEntity<>("Successfully deleted the comment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the comment", HttpStatus.BAD_REQUEST);
        }
    }
}