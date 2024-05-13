package com.cartelera.serviciosImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cartelera.entidades.Usuario;
import com.cartelera.repositorios.UsuarioRepositorio;
import com.cartelera.servicios.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarUser(Usuario usuario){
        usuario.setFecha_registro(LocalDateTime.now());
        usuario.setFecha_modificacion(LocalDateTime.now());
        String contraEncriptada = this.passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraEncriptada);
        return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario buscaPorIdUsuario(Long idUsuario) {
        Optional<Usuario> usuario = this.usuarioRepositorio.findById(idUsuario);
        return usuario.isPresent() ? usuario.get() : null;
}

    @Override
    public List<Usuario> buscaTodosUsuarios() {
        List<Usuario> usuarios = this.usuarioRepositorio.findAll();
        return usuarios;
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
            this.usuarioRepositorio.deleteById(idUsuario);
      
    }

    @Override
    public Usuario buscaUsuarioPorNombre(String nombreUsuario){
        return this.usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeNombreUsuario(String nombreUsuario){
        return this.usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCorreo(String correo){
        return this.usuarioRepositorio.existsByCorreo(correo);
    }

    @Override
    public Usuario inicioSesion(String nombreUsuario, String contraseña) {
        Usuario usuario = this.buscaUsuarioPorNombre(nombreUsuario);
        
        if (!this.passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            return null;
        }
        return usuario;

    }
}
