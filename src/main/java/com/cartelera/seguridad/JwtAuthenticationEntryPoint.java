package com.cartelera.seguridad;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException authException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("Acceso no autorizado. Inicie sesión o aporte credenciales válidas");
            writer.close();
            }

}
