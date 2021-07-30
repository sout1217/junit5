package com.inflearn.junit5;


import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyTest {

    int value = 1;

    @Order(2)
    @Test
    @Disabled
    void test1() {
        System.out.println("test 1");
        System.out.println(value++);
    }

    @Order(1)
    @Test
    void test2() {
        System.out.println("test 2");
        System.out.println(value++);

    }

    @Order(4)
    @Test
    void test3() {
        System.out.println("test 3");
        System.out.println(value++);

    }

    @Order(3)
    @Test
    void test4() {
        System.out.println("test 4");
        System.out.println(value++);

    }
}
