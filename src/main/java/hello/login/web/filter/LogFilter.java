package hello.login.web.filter;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestURI = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        String method = httpServletRequest.getMethod();
        int beforeTime = 0, afterTime = 0;
        //logback mdc
        try {
            LocalDateTime before = LocalDateTime.now();
            beforeTime = before.getNano();
            log.info("REQUEST [{}] {} {}", uuid, method, requestURI);
            chain.doFilter(request, response); //다음 체인 실행
        } catch (Exception e) {
            throw  e;
        }finally {
            LocalDateTime after = LocalDateTime.now();
            afterTime = after.getNano();
            log.info("Duration Time : {}ns", afterTime - beforeTime);
            log.info("RESPONE [{}] {} {}", uuid,method,requestURI);

        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
