package com.inflearn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


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

        // Mock Stubbing
        when(memberServiceMock.findById(any()))
                .thenReturn(Optional.of(member));

        assertEquals("root@gmail.com", memberServiceMock.findById(1L).get().getEmail());
        assertEquals("root@gmail.com", memberServiceMock.findById(2L).get().getEmail());

//         1L 값으로 호출이 되면 에러를 던진다
        when(memberServiceMock.findById(1L)).thenThrow(new IllegalArgumentException("error"));


        IllegalArgumentException memberFind = assertThrows(IllegalArgumentException.class, () -> {
            memberServiceMock.findById(1L);
        });

        assertEquals("error", memberFind.getMessage());

        // 1L 값으로 validate 를 호출하면 에러를 던지게 스터빙이 가능하다
        doThrow(new IllegalArgumentException("오류")).when(memberServiceMock).validate(1L);

        IllegalArgumentException memberValidate = assertThrows(IllegalArgumentException.class, () -> {
            memberServiceMock.validate(1L);
        });

        assertEquals("오류", memberValidate.getMessage());
    }

    @Test
    void createStudy2() {
        Member member = new Member();

        member.setId(1L);
        member.setEmail("root@gmail.com");


        // 1번째 호출 될 때, 2번째 호출 될 때, 3번째 호출 될 때 처리할 수 있다
        when(memberServiceMock.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new IllegalArgumentException("오류@@"))
                .thenReturn(Optional.empty())
        ;

        assertEquals("root@gmail.com", memberServiceMock.findById(1L).get().getEmail());
        assertThrows(IllegalArgumentException.class, () -> memberServiceMock.findById(2L));
        assertEquals(Optional.empty(),  memberServiceMock.findById(1L));
    }

}