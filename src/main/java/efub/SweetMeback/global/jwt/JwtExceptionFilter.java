package efub.SweetMeback.global.jwt;

import efub.SweetMeback.domain.global.exception.CustomException;
import io.jsonwebtoken.JwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


import lombok.NoArgsConstructor;
@NoArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            // JWT 예외 처리
            log.error("[-] Invalid Token");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token"); // 401 Unauthorized 응답 반환
        } catch (CustomException e) {
            // Custom 예외 처리 (필요에 따라 다른 상태 코드 반환 가능)
            log.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()); // 400 Bad Request 또는 다른 상태 코드로 응답
        }
    }

}

