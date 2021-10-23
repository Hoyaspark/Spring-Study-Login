package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.annotation.Login;
import hello.login.web.session.SessionManger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManger sessionManger;

//    @GetMapping("/")
//    public String home() {
//
//        return "home";
//    }

//    @GetMapping("/")
//    public String home(HttpServletRequest request, Model model) {
//        HttpSession httpSession = request.getSession(false);
//        if (httpSession == null) {
//            return "home";
//        }
//
//        Object session = httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        if (session == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", (Member) session);
//
//        return "loginHome";
//    }

//    @GetMapping("/")
//    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, Model model) {
//
//        if (loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", loginMember);
//
//        return "loginHome";
//    }

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model) {

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);

        return "loginHome";
    }
}