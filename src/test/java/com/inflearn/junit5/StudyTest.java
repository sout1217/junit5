package com.inflearn.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class StudyTest {

    @Test
    @DisplayName("테스트 1")
    void assume_true_test() {
        String test_env = System.getenv("USERNAME");
        System.out.println(test_env);

        // TRUE 를 리턴하는 경우에만 다음 코드를 실행한다
        assumeTrue("hbh".equals(test_env));

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("테스트 2")
    void assuming_that_test() {
        String username = System.getenv("USERNAME");
        System.out.println(username);

        assumingThat("system".equalsIgnoreCase(username), () -> {
            System.out.println("system 일 때 실행");
        });

        assumingThat("hbh".equalsIgnoreCase(username), () -> {
            System.out.println("사용자 명일 때 일 때 실행");
        });

        System.out.println("모두 실행됨");
    }

    @Test
    @DisplayName("테스트 3")
    @EnabledOnOs(OS.MAC)
    void annotation_os_one_test() {
        System.out.println("Hello Mac");
        String username = System.getenv("USERNAME");
        assumingThat("hbh".equalsIgnoreCase(username), () -> {
            System.out.println("something test");
        });
    }

    @Test
    @DisplayName("테스트 4")
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void annotation_os_array_test() {
        System.out.println("Hello Window, Mac");
    }

    @Test
    @DisplayName("테스트 5")
    @EnabledOnJre(JRE.JAVA_9)
    void annotation_jre_test() {
        System.out.println("Hello java 9");
    }


    @Test
    @DisplayName("테스트 6")
    @EnabledIfEnvironmentVariable(named = "USERNAME", matches = "hbh")
    void annotation_environment_var_test() {
        System.out.println("Hello hbh");
    }
}
