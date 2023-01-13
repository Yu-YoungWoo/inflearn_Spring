package hello.login.session;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        // 세션 생성 (서버에서 쿠키와 세션을 만들어 클라이언트에 전달)
        // (서버 -> 클라이언트)
        MockHttpServletResponse response = new MockHttpServletResponse();  // 가짜 응답
        Member member = new Member();
        sessionManager.createSession(member, response);


        // 요청에 응답 쿠키 저장 (클라이언트에서 위에서 생성한 세션을 담은 쿠키를 전달 받음)
        // (클라이언트 -> 서버)
        MockHttpServletRequest request = new MockHttpServletRequest(); // 가짜 요청
        request.setCookies(response.getCookies());


        // 세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
