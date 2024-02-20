package com.collaborateam.www.service;

import com.collaborateam.www.domain.FieldDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FieldServiceImplTest {
    @Autowired
    FieldService fieldService;
    FieldDto fieldDto1;
    FieldDto fieldDto2;

    @Before
    public void init() throws Exception {
        fieldDto1 = new FieldDto("IT", "Information Technology");
        fieldDto2 = new FieldDto("FIN", "Finance");

        fieldService.deleteAll();
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        List<FieldDto> list = fieldService.getList();
        assertEquals(0, list.size());

        assertEquals(1, fieldService.create(fieldDto1));
        list = fieldService.getList();
        assertEquals(1, list.size());

        assertEquals(1, fieldService.create(fieldDto2));
        list = fieldService.getList();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());

        fieldService.deleteAll();
        assertEquals(0, fieldService.getCount());
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        Integer fno = fieldService.getList().get(0).getFno();
        fieldDto1.setFno(fno);

        FieldDto fieldDto3 = fieldService.read(fno);
        assertEquals(fieldDto1, fieldDto3);

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());

        fno = fieldService.getList().get(1).getFno();
        fieldDto2.setFno(fno);

        FieldDto fieldDto4 = fieldService.read(fno);
        assertEquals(fieldDto2, fieldDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        Integer fno = fieldService.getList().get(0).getFno();

        fieldDto1.setFno(fno);
        fieldDto1.setName("ENG");
        fieldDto1.setValue("Engineering");

        assertEquals(1, fieldService.update(fieldDto1));

        FieldDto fieldDto3 = fieldService.read(fno);
        assertEquals(fieldDto1, fieldDto3);

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());

        fno = fieldService.getList().get(1).getFno();

        fieldDto2.setFno(fno);
        fieldDto2.setName("MED");
        fieldDto2.setValue("Medicine");

        assertEquals(1, fieldService.update(fieldDto2));

        FieldDto fieldDto4 = fieldService.read(fno);
        assertEquals(fieldDto2, fieldDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto1));
        assertEquals(1, fieldService.getCount());

        assertEquals(1, fieldService.create(fieldDto2));
        assertEquals(2, fieldService.getCount());

        Integer fno = fieldService.getList().get(0).getFno();

        assertEquals(0, fieldService.delete(fno + 111));
        assertEquals(1, fieldService.delete(fno));
        assertEquals(1, fieldService.getCount());
        assertNull(fieldService.read(fno));

        fno = fieldService.getList().get(0).getFno();

        assertEquals(0, fieldService.delete(fno + 222));
        assertEquals(1, fieldService.delete(fno));
        assertEquals(0, fieldService.getCount());
        assertNull(fieldService.read(fno));
    }
}