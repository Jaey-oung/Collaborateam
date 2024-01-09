package com.collaborateam.www.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaginationTest {
    @Test
    public void test() {
        Pagination pagination = new Pagination(200, 1, 10);
        pagination.print();
        assertFalse(pagination.isShowPrev());
        assertTrue(pagination.isShowNext());
        assertEquals(20, pagination.getTotalPage());
        assertEquals(1, pagination.getBeginPage());
        assertEquals(10, pagination.getEndPage());
    }

    @Test
    public void test2() {
        Pagination pagination = new Pagination(350, 11, 10);
        pagination.print();
        assertTrue(pagination.isShowPrev());
        assertTrue(pagination.isShowNext());
        assertEquals(35, pagination.getTotalPage());
        assertEquals(11, pagination.getBeginPage());
        assertEquals(20, pagination.getEndPage());
    }

    @Test
    public void test3() {
        Pagination pagination = new Pagination(555, 21, 10);
        pagination.print();
        assertTrue(pagination.isShowPrev());
        assertTrue(pagination.isShowNext());
        assertEquals(56, pagination.getTotalPage());
        assertEquals(21, pagination.getBeginPage());
        assertEquals(30, pagination.getEndPage());
    }
}