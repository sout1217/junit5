package com.inflearn.junit5;


import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest {


    // 보통은 유닛 테스트간의 의존성이 있으면 안되지만
    // use case 처럼 나뉘어서 테스트를 작성하는 경우에는 순차적으로 할 필요성이 있다
    // 예를 들어 유저가 로그인 하고 -> 마이페이지에서 내 정보를 조회하는 것처럼 case 를 순차적으로 하기 위함 (

    @Order(2)
    @Test
    void test1() {
        System.out.println("test 1");
    }

    @Order(1)
    @Test
    void test2() {
        System.out.println("test 2");
    }

    @Order(4)
    @Test
    void test3() {
        System.out.println("test 3");
    }

    @Order(3)
    @Test
    void test4() {
        System.out.println("test 4");
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("before All");
    }
}
