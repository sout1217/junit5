package com.inflearn.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudyTest {

    @Test
    void create1() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create 1");
    }

    @Test
    @Disabled
    void create2() {
        System.out.println("create 2");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("------Before All------");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("------After All------");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("---Before Each---");
    }

    @AfterEach
    void afterEach() {
        System.out.println("---After Each---");
    }

}
