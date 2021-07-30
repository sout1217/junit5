package com.inflearn.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class StudyTest {

    @Test
    @DisplayName("스터디 만들기 - 성공 💪")
    void create_study_success() {
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.");
    }

    @Test
    @DisplayName("스터디 만들기 - 실패")
    void create_study_fail() {
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.Nothing, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.";
            }
        });
    }

    @Test
    @DisplayName("스터디 만들기 - 에러 메시지 만들기")
    void create_study_fail_lamda() {
        Study study = new Study();
        assertNotNull(study);
        // 람다를 이용하면 실패한 경우에만 string 연산을 수행합니다, 반면 바로 string 값을 주고 변수와 합친다면 테스트 코드가 실패하던 성공하던 연산을 수행합니다
        assertEquals(StudyStatus.Nothing, study.getStatus(), () -> "람다를 이용하면 최대한 필요한 시점에 Stirng 연산을 합니다" + StudyStatus.DRAFT);
    }

    @Test
    @DisplayName("스터디 만들기 - limit")
    void create_study_fail_limit() {
        Study study = new Study(-10);
        assertNotNull(study);


        // 테스트 코드를 묶어서 모두 실행 한 결과를 보여줄 때 사용한다
        assertAll(
                () -> {
                    assertEquals(StudyStatus.Nothing, study.getStatus(), () -> "람다를 이용하면 최대한 필요한 시점에 Stirng 연산을 합니다" + StudyStatus.DRAFT);
                },
                () -> {
                    assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0 보다 커야 한다");
                }
        );
    }

    @Test
    @DisplayName("스터디 만들기 - 에러 메시지 만들기")
    void create_study_error() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Study(-10);
        });

        assertEquals("limit 은 0보다 커야 한다", exception.getMessage() );

    }

    @Test
    @DisplayName("스터디 만들기 - 100ms 안에 끝내야 한다")
    void create_study_duration_100ms() {

        // 100ms 안에 통과하는 지 확인한다
        // 단점은 기다리는 시간동안 다음 코드문을 실행 할 수 없다는 점이다
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }


    @Test
    @DisplayName("스터디 만들기 - 100ms 지나면 오류를 낸다")
    void create_study_duration_100ms_preemptively() {

        // 유의 해야 할 점이 있다
        // 쓰레드를 새로 만들어서 사용하기 때문에 ()->{} 안에서 쓰레드 로컬을 사용하는 곳에서 예상치 못한 상황이 일어날 수 있다

        // 스프링 트랜잭션은 쓰레드 로컬을 기본전략으로 사용하기 때문에 쓰레드 로컬은 다른 쓰레드와 공유가 안되기 때문에
        // 그 경우에 스프링이 만든 트랜젝션이 제데로 적용이 안 될 수 있다
        // -> 주로 롤백이 안되고 반영이 될 수 있다
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

}
