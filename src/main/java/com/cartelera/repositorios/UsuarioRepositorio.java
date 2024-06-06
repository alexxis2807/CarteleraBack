package com.cartelera.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartelera.entidades.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Usuario findByNombreUsuario(String nombreUsuario);
    public boolean existsByNombreUsuario(String nombreUsuario);
    public boolean existsByCorreo(String correo);
}
