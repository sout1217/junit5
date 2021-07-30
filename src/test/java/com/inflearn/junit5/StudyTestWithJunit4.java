package com.inflearn.junit5;

import org.junit.Before;
import org.junit.Test;

public class StudyTestWithJunit4 {


    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void test() {
        System.out.println("test 1");
    }


    @Test
    public void test2() {
        System.out.println("test 2");
    }
}
