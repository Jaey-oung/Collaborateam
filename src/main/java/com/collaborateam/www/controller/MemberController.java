package com.collaborateam.www.controller;

import com.collaborateam.www.domain.MemberDto;
import com.collaborateam.www.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping("/members")
    public ResponseEntity<List<MemberDto>> list(Integer tno) {
        List<MemberDto> list;
        try {
            list = memberService.getList(tno);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/members")
    public ResponseEntity<String> create(@RequestBody MemberDto memberDto) {
        try {
            int rowCnt = memberService.create(memberDto);

            if(rowCnt != 1)
                return new ResponseEntity<>("Already created the member", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>("Successfully created the member", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create the member", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/members/delete/{mno}")
    public ResponseEntity<String> delete(@PathVariable Integer mno, String id) {
        try {
            int rowCnt = memberService.delete(mno, id);

            if(rowCnt != 1)
                throw new Exception("Member delete failed");

            return new ResponseEntity<>("Successfully deleted the member", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the member", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/members/withdraw/{tno}")
    public ResponseEntity<String> withdraw(@PathVariable Integer tno, HttpSession session) {
        String id = (String) session.getAttribute("id");

        try {
            int rowCnt = memberService.withdraw(tno, id);

            if(rowCnt != 1)
                throw new Exception("Team withdraw failed");

            return new ResponseEntity<>("Successfully withdrew from the team", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to withdraw from the team", HttpStatus.BAD_REQUEST);
        }
    }
}