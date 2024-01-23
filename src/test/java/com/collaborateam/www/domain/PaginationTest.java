//package com.collaborateam.www.domain;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PaginationTest {
//    @Test
//    public void test() {
//        BoardListCondition blc = new BoardListCondition(1, 10, "IT", "WD", "", "");
//        Pagination pagination = new Pagination(200, blc);
//
//        pagination.print();
//        assertEquals(20, pagination.getTotalPage());
//        assertEquals(1, pagination.getBeginPage());
//        assertEquals(10, pagination.getEndPage());
//        assertFalse(pagination.isShowPrev());
//        assertTrue(pagination.isShowNext());
//    }
//
//    @Test
//    public void test2() {
//        BoardListCondition blc = new BoardListCondition(11, 10, "IT", "Web Development", "", "");
//        Pagination pagination = new Pagination(350, blc);
//
//        pagination.print();
//        assertEquals(35, pagination.getTotalPage());
//        assertEquals(11, pagination.getBeginPage());
//        assertEquals(20, pagination.getEndPage());
//        assertTrue(pagination.isShowPrev());
//        assertTrue(pagination.isShowNext());
//    }
//
//    @Test
//    public void test3() {
//        BoardListCondition blc = new BoardListCondition(21, 10, "IT", "Web Development", "", "");
//        Pagination pagination = new Pagination(555, blc);
//
//        pagination.print();
//        assertEquals(56, pagination.getTotalPage());
//        assertEquals(21, pagination.getBeginPage());
//        assertEquals(30, pagination.getEndPage());
//        assertTrue(pagination.isShowPrev());
//        assertTrue(pagination.isShowNext());
//    }
//}