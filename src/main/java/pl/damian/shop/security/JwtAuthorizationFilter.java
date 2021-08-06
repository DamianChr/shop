package pl.damian.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        Claims claims = Jwts.parser()
                .setSigningKey("QWERTY")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        String email = claims.getSubject();
        if (email != null) {
            String authorities = claims.get("authorities", String.class);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (authorities != null && !authorities.isEmpty()) {
                grantedAuthorities = Arrays.stream(authorities.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);//wstawiamy do conteks. usera dla którego wykonywany jest request
            chain.doFilter(request, response);//request wykonuje sie do konca
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }


    }
}
