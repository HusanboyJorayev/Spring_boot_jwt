package com.example.spring_boot_jwt.config;

import com.example.spring_boot_jwt.user.UserDetailsImpl;
import com.example.spring_boot_jwt.user.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final JWTTokenUtil jwtTokenUtil;
    private final UserDetailsImpl userDetailsImpl;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || header.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        if (!jwtTokenUtil.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtTokenUtil.getUsername(token);
        UserDto dto = (UserDto) userDetailsImpl.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, dto.getAuthorities());
        WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(request);
        authenticationToken.setDetails(details);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
