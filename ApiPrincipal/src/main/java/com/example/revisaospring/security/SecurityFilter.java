package com.example.revisaospring.security;


import com.example.revisaospring.repositories.UsuarioRepository;
import com.example.revisaospring.services.implementation.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    UsuarioRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
       // System.out.println("AQUIIII" + token);
        if(token != null) {
           // System.out.println("ENTROUUU");
            var email = tokenService.validarToken(token);
            System.out.println("SIMPLEMENTEED"+ email);
            UserDetails userDetails = repository.findByEmail((String) email);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("AINDA NENHUM TOKEN?" + token);
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }

}
