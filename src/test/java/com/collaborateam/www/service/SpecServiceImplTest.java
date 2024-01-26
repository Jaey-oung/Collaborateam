package com.collaborateam.www.service;

import com.collaborateam.www.domain.SpecDto;
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
public class SpecServiceImplTest {
    @Autowired
    SpecService specService;
    SpecDto specDto1;
    SpecDto specDto2;

    @Before
    public void init() throws Exception {
        specDto1 = new SpecDto("WD", "Web Development", "IT");
        specDto2 = new SpecDto("RM", "Risk Management", "FIN");

        specService.deleteAll();
    }

    @Test
    public void insertData() throws Exception {
        SpecDto specDto = new SpecDto("A", "All", "A");
        specService.create(specDto);
        specDto = new SpecDto("WD", "Web Development", "IT");
        specService.create(specDto);
        specDto = new SpecDto("SD", "Software Development", "IT");
        specService.create(specDto);
        specDto = new SpecDto("FA", "Financial Analysis", "FIN");
        specService.create(specDto);
        specDto = new SpecDto("RM", "Risk Management", "FIN");
        specService.create(specDto);
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, specService.getCount());

        List<SpecDto> list = specService.getList();
        assertEquals(0, list.size());

        assertEquals(1, specService.create(specDto1));
        list = specService.getList();
        assertEquals(1, list.size());

        assertEquals(1, specService.create(specDto2));
        list = specService.getList();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());

        specService.deleteAll();
        assertEquals(0, specService.getCount());
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        Integer sno = specService.getList().get(0).getSno();
        specDto1.setSno(sno);

        SpecDto specDto3 = specService.read(sno);
        assertEquals(specDto1, specDto3);

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());

        sno = specService.getList().get(1).getSno();
        specDto2.setSno(sno);

        SpecDto specDto4 = specService.read(sno);
        assertEquals(specDto2, specDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        Integer sno = specService.getList().get(0).getSno();

        specDto1.setSno(sno);
        specDto1.setValue("EE");
        specDto1.setName("Electrical Engineering");
        specDto1.setField("Engineering");

        assertEquals(1, specService.update(specDto1));

        SpecDto specDto3 = specService.read(sno);
        assertEquals(specDto1, specDto3);

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());

        sno = specService.getList().get(1).getSno();

        specDto2.setSno(sno);
        specDto2.setName("CAD");
        specDto2.setValue("Cardiology");
        specDto2.setField("Medicine");

        assertEquals(1, specService.update(specDto2));

        SpecDto specDto4 = specService.read(sno);
        assertEquals(specDto2, specDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());

        Integer sno = specService.getList().get(0).getSno();

        assertEquals(0, specService.delete(sno + 111));
        assertEquals(1, specService.delete(sno));
        assertEquals(1, specService.getCount());
        assertNull(specService.read(sno));

        sno = specService.getList().get(0).getSno();

        assertEquals(0, specService.delete(sno + 222));
        assertEquals(1, specService.delete(sno));
        assertEquals(0, specService.getCount());
        assertNull(specService.read(sno));
    }

    @Test
    public void getSpecsByFieldTest() throws Exception {
        assertEquals(0, specService.getCount());

        assertEquals(1, specService.create(specDto1));
        assertEquals(1, specService.getCount());

        assertEquals(1, specService.create(specDto2));
        assertEquals(2, specService.getCount());

        List<SpecDto> list = specService.getSpecsByField(specDto1.getField());
        assertEquals(1, list.size());

        list = specService.getSpecsByField(specDto2.getField());
        assertEquals(1, list.size());

        list = specService.getSpecsByField("TEST");
        assertEquals(0, list.size());
    }
}