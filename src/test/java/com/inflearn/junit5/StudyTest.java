package com.inflearn.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest {

    int value = 1;

    // 메소드마다 서로다른 인스턴스 이기 때문에
    // value 가 어떤 테스트에서든 1이다
    // 테스트간의 의존성을 줄이기위해 새로운 인스턴스를 만드는 전략을 한 것 이다

    // jUnit5 에서는 그나마 기본적으로 선언된 순서대로 실행은 되지만,
    // 원래는 정해진 순서가 없어 코드가 많아지면 순서대로 실행이 되지 않을 때 가 있다

    // 이러한 기본전략을 바꾸는 방법이 JUNIT5 에 추가 되었다
    // 새로운 인스턴스를 계속 만들지 않기 때문에 조금이나마 성능 장점이 있다
    // 테스트 인스턴스가 PER CLASS 인 경우 @BeforeAll 또는 @AfterAll 은 더 이상 static 으로 작성될 필요가 없다

    @Test
    @DisplayName("스터디 만들기")
    void create_study() {
        System.out.println(value++);
        System.out.println("test 1");
    }

    @Test
    @DisplayName("스터디 만들기")
    void create_study2() {
        System.out.println(value++);
        System.out.println("test 2");
    }

}
