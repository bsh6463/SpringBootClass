package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //각 method실행 끝날대마다 실행
    public void afterEach(){
        memberRepository.clearStore();
    }


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


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //예외 터진걸 잡으려면 try - cath쓰면됨

            //member2 저장시 IllegalStateException 터져야함.
/*        try{
            memberService.join(member2);
            //만약 여기서 catch로 안넘어가면 fail상황임
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}