package com.inflearn.junit5;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws IllegalArgumentException;

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
