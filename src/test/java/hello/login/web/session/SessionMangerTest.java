package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SessionMangerTest {

    SessionManger sessionManger = new SessionManger();

    @Test
    void sessionTest() {
        // given
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManger.createSession(member,response);

        // when
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        Object session = sessionManger.getSession(request);

        // then
        assertThat(session).isEqualTo(member);

        // Expire Session
        sessionManger.expire(request);

        assertThat(sessionManger.getSession(request)).isNull();
    }

    @Test
    void sessionTestV2() {
        // request에서 session을 가져옴
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession(true);// session이 존재하지 않으면 생성, 있으면 그대로 사용

        // session에 name = loginMember,  value = testMember Object 넣음 Map<String,Object>
        Member testMember = createTestMember();
        session.setAttribute("loginMember", testMember);

        HttpSession session1 = request.getSession();

        System.out.println(session1.getId());

        session1.getAttributeNames().asIterator()
                .forEachRemaining(name -> System.out.println("name = " + name + " value = " + session1.getAttribute(name)));

//        assertThat(session1.get()).isEqualTo("JSESSIONID");
        assertThat(session1.getAttribute("loginMember")).isEqualTo(testMember);
    }

    private Member createTestMember() {
        Member member = new Member();
        member.setName("test");
        member.setLoginId("test");
        member.setPassword("test!");

        return member;
    }

}