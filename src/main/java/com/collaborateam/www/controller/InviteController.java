package com.collaborateam.www.controller;

import com.collaborateam.www.domain.InviteDto;
import com.collaborateam.www.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InviteController {
    @Autowired
    InviteService inviteService;

    @RequestMapping("/invites")
    public ResponseEntity<List<InviteDto>> list(String id) {
        List<InviteDto> list;
        try {
            list = inviteService.getUserInvitation(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/invites")
    public ResponseEntity<String> write(@RequestBody InviteDto inviteDto) {
        try {
            int rowCnt = inviteService.create(inviteDto);

            if (rowCnt != 1)
                throw new Exception("Member invite failed");

            return new ResponseEntity<>("Successfully invited the member", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to invite the member", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/invites/{ino}")
    public ResponseEntity<String> remove(@PathVariable Integer ino) {
        try {
            int rowCnt = inviteService.remove(ino);

            if(rowCnt != 1)
                throw new Exception("Invite remove failed");

            return new ResponseEntity<>("Successfully removed the invite", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove the invite", HttpStatus.BAD_REQUEST);
        }
    }
}