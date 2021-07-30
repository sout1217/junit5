package com.inflearn.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class FastTest {

    @Test
    @DisplayName("Fast Test Tag")
    @Tag("fast")
    void fast_test() {
        System.out.println("FastTest 클래스의 Test");
    }
}
