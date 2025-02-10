package by.smertex.api.filter;

import by.smertex.core.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";

    private static final String BEARER = "Bearer ";

    private final ObjectMapper objectMapper;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER);
        String username = null;

        if (authHeader != null && authHeader.startsWith(BEARER)) {
            String jwt = authHeader.substring(BEARER.length());
            try {
                username = jwtUtil.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                response.getWriter().write(
                        objectMapper.writeValueAsString(responseException(response, "Expired token exception"))
                );
            } catch (SignatureException e) {
                response.getWriter().write(
                        objectMapper.writeValueAsString(responseException(response, "Signature token exception"))
                );
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private String responseException(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return message;
    }
}
