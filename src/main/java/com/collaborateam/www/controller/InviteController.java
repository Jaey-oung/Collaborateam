package com.collaborateam.www.controller;

import com.collaborateam.www.domain.InviteDto;
import com.collaborateam.www.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class InviteController {
    @Autowired
    InviteService inviteService;

    @RequestMapping("/invites")
    public ResponseEntity<List<InviteDto>> list(HttpSession session) {
        String id = (String)session.getAttribute("id");
        List<InviteDto> list;

        try {
            list = inviteService.getUserInvite(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/invites")
    public ResponseEntity<String> create(@RequestBody InviteDto inviteDto) {
        try {
            int rowCnt = inviteService.create(inviteDto);

            if (rowCnt != 1)
                return new ResponseEntity<>("Already invited the member", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>("Successfully invited the member", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to invite the member", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/invites/{ino}")
    public ResponseEntity<String> delete(@PathVariable Integer ino, HttpSession session) {
        String id = (String)session.getAttribute("id");

        try {
            int rowCnt = inviteService.delete(ino, id);

            if(rowCnt != 1)
                throw new Exception("Invite delete failed");

            return new ResponseEntity<>("Successfully deleted the invite", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the invite", HttpStatus.BAD_REQUEST);
        }
    }
}