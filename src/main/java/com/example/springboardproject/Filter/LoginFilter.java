package com.example.springboardproject.Filter;

import com.example.springboardproject.member.exception.NotFoundSessionKeyException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    // 인증을 하지 않아도될 URL Path 배열
    private static final String[] WHITE_LIST = {"/", "/members", "/auth/login"};

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 다운캐스팅 실행
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        // 다운캐스팅 실행
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        log.info("로그인필터 로직 실행");
        log.info("httpRequest {}", httpRequest);
        log.info("httpResponse {}", httpResponse);

        // isWhiteList 에 포함된 경우 ture -> !ture -> false
        if (!isWhiteList(requestURI)) {

            // 로그인 확인 -> 로그인하면 session에 값이 저장되어 있다는 가정.
            // 세션이 존재하면 가져온다. 세션이 없으면 session = null
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute("세션키값") == null) {
                log.info("로그아웃 성공");
                throw new NotFoundSessionKeyException();
            }
        }
        log.info("로그인 성공");
        // 1번경우 : whiteListURL에 등록된 URL 요청이면 바로 filterChain.doFilter()
        // 2번경우 : 필터 로직 통과 후 다음 필터 호출 filterChain.doFilter()
        // 다음 필터 없으면 Servlet -> Controller 호출하거나, 다른 Filter 존재시에 다음 Filter 호출

        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 로그인 여부를 확인하는 URL인지 체크하는 메서드
    private boolean isWhiteList(String requestURI) {

        // request URI가 whiteListURL에 포함되는지 확인
        // 포함되면 true 반환
        // 포함되지 않으면 false 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }


}
