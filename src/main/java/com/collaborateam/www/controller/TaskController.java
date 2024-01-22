package com.collaborateam.www.controller;

import com.collaborateam.www.domain.TaskDto;
import com.collaborateam.www.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping("/tasks")
    public ResponseEntity<List<TaskDto>> list(Integer tno, String member) {
        List<TaskDto> list;
        try {
            list = taskService.getList(tno, member);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<String> write(@RequestBody TaskDto taskDto) {
        try {
            int rowCnt = taskService.create(taskDto);

            if(rowCnt != 1)
                throw new Exception("Task add failed");

            return new ResponseEntity<>("Successfully added the task", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add the task", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/tasks/{tano}")
    public ResponseEntity<String> modify(@PathVariable Integer tano, @RequestBody TaskDto taskDto) {
        taskDto.setTano(tano);
        try {
            int rowCnt = taskService.modify(taskDto);

            if(rowCnt != 1)
                throw new Exception("Task modify failed");

            return new ResponseEntity<>("Successfully modified the task", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify the task", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tasks/{tano}")
    public ResponseEntity<String> remove(@PathVariable Integer tano) {
        try {
            int rowCnt = taskService.remove(tano);

            if(rowCnt != 1)
                throw new Exception("Task remove failed");

            return new ResponseEntity<>("Successfully removed the task", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove the task", HttpStatus.BAD_REQUEST);
        }
    }
}