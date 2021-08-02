package com.inflearn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class StudyServiceTests {

    @Mock
    MemberService memberServiceMock;
    @Mock
    StudyRepository studyRepositoryMock;
    @InjectMocks
    StudyService studyService;

    @Test
    void createStudy() {

        Member member = new Member();
        member.setEmail("root@gmail.com");
        member.setId(1L);

        Study study = new Study(10, "테스트");

        given(memberServiceMock.findById(1L)).willReturn(Optional.of(member));
        given(studyRepositoryMock.save(study)).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);

        // then
        then(memberServiceMock).should(times(1)).notify(study); // notify(study) 메소드가 1번 호출 되어야 한다
        then(memberServiceMock).should(times(1)).notify(member); // notify(member) 메소드가 1번 호출 되어야 한다
        then(memberServiceMock).shouldHaveNoMoreInteractions(); // 더 이상 사용되지 않아야 한다
    }
}