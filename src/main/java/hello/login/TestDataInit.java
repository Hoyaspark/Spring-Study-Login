package hello.login;

import hello.login.domain.item.Item;
import hello.login.domain.item.ItemRepository;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public TestDataInit(ItemRepository itemRepository, MemberRepository memberRepository) {
        log.info("create @Bean TestDataInit");
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
        Member member = new Member();
        member.setLoginId("wk30815");
        member.setPassword("qhrcl!7861");
        member.setName("박상호");

        memberRepository.save(member);
    }

}