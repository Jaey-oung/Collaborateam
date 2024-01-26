package com.collaborateam.www.controller;

import com.collaborateam.www.domain.FieldDto;
import com.collaborateam.www.domain.SpecDto;
import com.collaborateam.www.service.FieldService;
import com.collaborateam.www.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardFilterController {
    @Autowired
    FieldService fieldService;
    @Autowired
    SpecService specService;

    @RequestMapping("/fields")
    public ResponseEntity<List<FieldDto>> fieldList() {
        List<FieldDto> list;
        try {
            list = fieldService.getList();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/specs")
    public ResponseEntity<List<SpecDto>> specList(String field) {
        List<SpecDto> list;
        try {
            list = specService.getSpecsByField(field);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}