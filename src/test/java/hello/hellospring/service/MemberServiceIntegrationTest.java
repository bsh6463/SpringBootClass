package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 테스트
@Transactional
class MemberServiceIntegrationTest {

    @Autowired //스프링한테 "내놔"
    MemberService memberService;

    @Autowired //스프링한테 "내놔"
    MemberRepository memberRepository;


    @Test //Test는 한글로 바꿔도됨 ㅋㅋㅋ
    void 회원가입() {
        //Test는 아래와 같은 문법으로 진행하자.
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());

    }

    //Test는 정상 flow도 좋지만 예외 flow가 갱장히 중요하다!
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}