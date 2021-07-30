package com.inflearn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class StudyServiceTests {

    @Mock MemberService memberServiceMock;
    @Mock StudyRepository studyRepositoryMock;
    @InjectMocks
    StudyService studyService;

    @Test
    void createStudy() {
        assertNotNull(studyService);
    }
}