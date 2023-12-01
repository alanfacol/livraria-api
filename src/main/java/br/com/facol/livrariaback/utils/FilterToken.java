package br.com.facol.livrariaback.utils;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.repository.LoginRepository;
import br.com.facol.livrariaback.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String token;

            String auth = request.getHeader("Authorization");

            if(auth != null){
                token = auth.replace("Bearer ","");
                String subject = this.tokenService.getSubject(token);

                Login usuario = this.loginRepository.findByUsername(subject);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro de autenticação: " + ex.getMessage());
        }

    }
}
