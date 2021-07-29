package com.inflearn.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudyTest {

    @Test
    @DisplayName("스터디 만들기 💪")
    void create_study() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create 1");
    }

    @Test
    @DisplayName("스터딩 ╰（‵□′）╯")
    void create_new_study_again() {
        System.out.println("create1");
    }

}
