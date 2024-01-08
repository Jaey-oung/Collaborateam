package com.collaborateam.www.service;

import com.collaborateam.www.domain.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    Calendar birth;
    UserDto userDto1;
    UserDto userDto2;

    @Before
    public void init() {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19); // 2000-12-19

        userDto1 = new UserDto("user1", "user1", "user1@user1.com", "user1", birth.getTime()); // Calendar -> Date
        userDto2 = new UserDto("user2", "user2", "user2@user2.com", "user2", birth.getTime()); // Calendar -> Date
    }

    @Test
    public void getCountTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        List<UserDto> userDtoList = userService.getList();
        assertEquals(0, userDtoList.size());

        assertEquals(1, userService.addUser(userDto1));
        userDtoList = userService.getList();
        assertEquals(1, userDtoList.size());

        assertEquals(1, userService.addUser(userDto2));
        userDtoList = userService.getList();
        assertEquals(2, userDtoList.size());
    }

    @Test
    public void removeAllUsersTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());

        userService.removeAllUsers();
        assertEquals(0, userService.getCount());
    }

    @Test
    public void addUserTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());
    }

    @Test
    public void getUserTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        String id = userService.getList().get(0).getId();
        UserDto userDto3 = userService.getUser(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());

        id = userService.getList().get(1).getId();
        UserDto userDto4 = userService.getUser(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void updateUserTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        birth.set(2002, Calendar.DECEMBER, 19);

        userDto1.setPwd("user10");
        userDto1.setEmail("user10@user10.com");
        userDto1.setName("user10");
        userDto1.setBirth(birth.getTime());

        assertEquals(1, userService.updateUser(userDto1));

        String id = userService.getList().get(0).getId();
        UserDto userDto3 = userService.getUser(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());

        birth.set(2004, Calendar.DECEMBER, 19);

        userDto2.setPwd("user20");
        userDto2.setEmail("user20@user20.com");
        userDto2.setName("user20");
        userDto2.setBirth(birth.getTime());

        assertEquals(1, userService.updateUser(userDto2));

        id = userService.getList().get(1).getId();
        UserDto userDto4 = userService.getUser(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void removeUserTest() throws Exception {
        userService.removeAllUsers();
        assertEquals(0, userService.getCount());

        assertEquals(1, userService.addUser(userDto1));
        assertEquals(1, userService.getCount());

        assertEquals(1, userService.addUser(userDto2));
        assertEquals(2, userService.getCount());

        assertEquals(0, userService.removeUser(userDto1.getId() + "111"));
        assertEquals(2, userService.getCount());

        assertEquals(1, userService.removeUser(userDto1.getId()));
        assertEquals(1, userService.getCount());
        assertNull(userService.getUser(userDto1.getId()));

        assertEquals(1, userService.removeUser(userDto2.getId()));
        assertEquals(0, userService.getCount());
        assertNull(userService.getUser(userDto2.getId()));

        assertEquals(0, userService.removeUser(userDto1.getId() + "222"));
        assertEquals(0, userService.getCount());
    }
}