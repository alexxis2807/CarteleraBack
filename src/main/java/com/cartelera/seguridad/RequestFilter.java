package com.cartelera.seguridad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cartelera.entidades.Usuario;
import com.cartelera.repositorios.UsuarioRepositorio;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestFilter extends OncePerRequestFilter{
@Autowired
UsuarioRepositorio usuarioRepositorio;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String username = request.getHeader("Username");
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX) && username != null) {
            String jwt = authorizationHeader.substring(TOKEN_PREFIX.length());
            Usuario usuario = this.usuarioRepositorio.findByNombreUsuario(username);
            if (usuario.getContraseña().equals(jwt)) {
                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.println("Acceso no autorizado. Inicie sesión o aporte credenciales válidas");
                writer.close();
            }
        }
        filterChain.doFilter(request, response);
    }
}
