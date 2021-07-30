package com.inflearn.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StudyTest {

    // 빨리 끝나는 테스트의 경우 로컬에서 테스트를 하길 원한다
    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast")
    void assume_true_test() {
        System.out.println("fast test");
    }


    // 오래걸리는 테스트의 경우 CI 에서 테스트를 하길 원한다
    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow")
    void slow_test() {
        System.out.println("slow test");
    }

}
