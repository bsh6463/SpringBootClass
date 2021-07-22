package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MemberRepository {

     Member save(Member member);

     Optional<Member> findById(Long id);

     Optional<Member> findByName(String name);

     List<Member> findAll();



}
