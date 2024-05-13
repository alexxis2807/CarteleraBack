package com.cartelera.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cartelera.entidades.Usuario;

@Service
public interface UsuarioServicio{
    public Usuario registrarUser(Usuario usuario);
    public Usuario buscaPorIdUsuario(Long idUsuario);
    public List<Usuario> buscaTodosUsuarios();
    public void eliminarUsuario(Long idUsuario);
    public Usuario buscaUsuarioPorNombre(String nombreUsuario);
    public boolean existeNombreUsuario(String nombreUsuario);
    public boolean existeCorreo(String correo);
    public Usuario inicioSesion(String nombreUsuario, String contraseña);

}
