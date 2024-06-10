package com.cartelera.seguridad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Autowired
JwtUtil jwtUtil;

@Value("${ipPermitidaSwagger}")
private String ipPermitidaSwagger;

private static final String AUTHORIZATION_HEADER = "Authorization";
private static final String TOKEN_PREFIX = "Bearer ";
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain)
throws ServletException, IOException {

if (request.getRemoteAddr().equals(ipPermitidaSwagger)) {
    Usuario usuario = this.usuarioRepositorio.findByNombreUsuario("administrador");
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        usuario, null, Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
    
}else{
    String passwordUsuario = null;
    String jwt = null;
    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
    String username = request.getHeader("Username");
    if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX) && username != null) {
    jwt = authorizationHeader.substring(TOKEN_PREFIX.length());
    try {
        passwordUsuario = jwtUtil.extractPassword(jwt);    
    } catch (IllegalArgumentException e) {
        filterChain.doFilter(request, response);
    }

    if (passwordUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        
    Usuario usuario = this.usuarioRepositorio.findByNombreUsuario(username);
    if (jwtUtil.validateToken(jwt, usuario.getContraseña())) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuario, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }else{
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("Acceso no autorizado. Inicie sesión o aporte credenciales válidas");
        writer.close();
    } } }
    filterChain.doFilter(request, response);
    }}}
