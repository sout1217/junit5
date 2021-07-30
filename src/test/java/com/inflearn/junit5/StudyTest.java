package com.inflearn.junit5;


import com.inflearn.junit5.annotations.FastTest;
import com.inflearn.junit5.annotations.SlowTest;
import org.junit.jupiter.api.DisplayName;

public class StudyTest {

    // 빨리 끝나는 테스트의 경우 로컬에서 테스트를 하길 원한다
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void assume_true_test() {
        System.out.println("fast test");
    }


    // 오래걸리는 테스트의 경우 CI 에서 테스트를 하길 원한다
    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void slow_test() {
        System.out.println("slow test");
    }

}
