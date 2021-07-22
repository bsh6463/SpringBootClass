package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    //repository를 직접 생성하는 것이 아니라, 외부에서 넣어주도록 변경.
    //MemberService입장에서 외부에서 Repository를 주입받음 = Dependancy Injection(DI)
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        //컨, 알트, m =method extraction.
        //Optional<Member> result =  memberRepository.findByName(member.getName());
        //optional로 한 번 감싸면 아래와 같이 처ㅣㄹ해 줄 수 있음
        //optional 없으면 if로 조건 걸어야함.

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){

        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
