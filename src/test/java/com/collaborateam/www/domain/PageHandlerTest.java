package com.collaborateam.www.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void test() {
        PageHandler pageHandler = new PageHandler(200, 1);
        pageHandler.print();
        assertFalse(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
        assertEquals(20, pageHandler.getTotalPage());
        assertEquals(1, pageHandler.getBeginPage());
        assertEquals(10, pageHandler.getEndPage());
    }

    @Test
    public void test2() {
        PageHandler pageHandler = new PageHandler(350, 11);
        pageHandler.print();
        assertTrue(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
        assertEquals(35, pageHandler.getTotalPage());
        assertEquals(11, pageHandler.getBeginPage());
        assertEquals(20, pageHandler.getEndPage());
    }

    @Test
    public void test3() {
        PageHandler pageHandler = new PageHandler(555, 21);
        pageHandler.print();
        assertTrue(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
        assertEquals(56, pageHandler.getTotalPage());
        assertEquals(21, pageHandler.getBeginPage());
        assertEquals(30, pageHandler.getEndPage());
    }
}