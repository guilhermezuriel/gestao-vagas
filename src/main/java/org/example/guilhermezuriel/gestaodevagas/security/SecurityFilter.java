package org.example.guilhermezuriel.gestaodevagas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;
import org.example.guilhermezuriel.gestaodevagas.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            SecurityContextHolder.getContext().setAuthentication(null);
            String header = request.getHeader("Authorization");

            if(request.getRequestURI().startsWith("/company")){
            if(header != null) {
                var  subjectToken = this.jwtProvider.validateToken(header);
                if(subjectToken.isEmpty()){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
                request.setAttribute("company_id", subjectToken);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subjectToken,  null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }}
            filterChain.doFilter(request, response);

    }
}
