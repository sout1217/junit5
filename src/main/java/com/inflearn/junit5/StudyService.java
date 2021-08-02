package com.inflearn.junit5;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> optionalMember = memberService.findById(memberId);

        study.setOwner(optionalMember.orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다")));

        Member member = optionalMember.get();

        memberService.notify(study);
        memberService.notify(member);

        return studyRepository.save(study);
    }
}
