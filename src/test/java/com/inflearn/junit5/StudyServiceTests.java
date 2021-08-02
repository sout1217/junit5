package com.inflearn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

        member.setId(1L);
        member.setEmail("root@gmail.com");

        Study study = new Study(10, "테스트");

        // 1번째 호출 될 때, 2번째 호출 될 때, 3번째 호출 될 때 처리할 수 있다
        when(memberServiceMock.findById(any())).thenReturn(Optional.of(member));


        studyService.createNewStudy(1L, study);

        // notify(study) 메솓가 딱 1번 호출되야 한다
        verify(memberServiceMock, times(1)).notify(study);

        // 1번도 실행이 안되었는 지
        verify(memberServiceMock, never()).validate(1L);

        // 순서대로 실행되었는 지 확인하는 작업
        InOrder inOrder = inOrder(memberServiceMock);
        inOrder.verify(memberServiceMock).notify(study);
        inOrder.verify(memberServiceMock).notify(member);

//        verifyNoMoreInteractions(memberServiceMock); // 어떤 액션 이 후에 mock 을 사용하지 않아야 할 때 사용한다

//        verify(memberServiceMock, times(100)).notify(member); // 특정 시간 내에 해당 테스트를 성공해야 하는 경우
    }

}