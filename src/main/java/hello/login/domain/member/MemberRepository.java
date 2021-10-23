package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public MemberRepository() {
        log.info("create @Bean MemberRepository");
    }

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member {}", member);
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id) {
        log.info("findById");
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        List<Member> all = findAll();
        return all.stream().filter(m -> m.getLoginId().equals(loginId)).findAny();
    }

    public List<Member> findAll() {
        return store.values().stream().collect(Collectors.toList());
    }

    public void clearStore(){
        store.clear();
    }
}

