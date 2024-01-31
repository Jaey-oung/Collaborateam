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
    public ResponseEntity<String> create(@RequestBody GoalDto goalDto, Integer tno) {
        goalDto.setTno(tno);
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
    public ResponseEntity<String> update(@PathVariable Integer gno, @RequestBody GoalDto goalDto) {
        goalDto.setGno(gno);
        try {
            int rowCnt = goalService.update(goalDto);

            if(rowCnt != 1)
                throw new Exception("Goal modify failed");

            return new ResponseEntity<>("Successfully modified the goal", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify the goal", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/goals/{gno}")
    public ResponseEntity<String> delete(@PathVariable Integer gno) {
        try {
            int rowCnt = goalService.delete(gno);

            if(rowCnt != 1)
                throw new Exception("Goal delete failed");

            return new ResponseEntity<>("Successfully deleted the goal", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the goal", HttpStatus.BAD_REQUEST);
        }
    }
}