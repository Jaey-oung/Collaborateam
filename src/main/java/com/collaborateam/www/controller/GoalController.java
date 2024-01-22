package com.collaborateam.www.controller;

import com.collaborateam.www.domain.GoalDto;
import com.collaborateam.www.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {
    @Autowired
    GoalService goalService;

    @RequestMapping("/goals")
    public ResponseEntity<List<GoalDto>> list(Integer tno) {
        List<GoalDto> list;
        try {
            list = goalService.getList(tno);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/goals")
    public ResponseEntity<String> write(@RequestBody GoalDto goalDto) {
        try {
            int rowCnt = goalService.create(goalDto);

            if(rowCnt != 1)
                throw new Exception("Goal add failed");

            return new ResponseEntity<>("Successfully added the goal", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add the goal", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/goals/{gno}")
    public ResponseEntity<String> modify(@PathVariable Integer gno, @RequestBody GoalDto goalDto) {
        goalDto.setGno(gno);
        try {
            int rowCnt = goalService.modify(goalDto);

            if(rowCnt != 1)
                throw new Exception("Goal modify failed");

            return new ResponseEntity<>("Successfully modified the goal", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify the goal", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/goals/{gno}")
    public ResponseEntity<String> remove(@PathVariable Integer gno) {
        try {
            int rowCnt = goalService.remove(gno);

            if(rowCnt != 1)
                throw new Exception("Goal remove failed");

            return new ResponseEntity<>("Successfully removed the goal", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove the goal", HttpStatus.BAD_REQUEST);
        }
    }
}