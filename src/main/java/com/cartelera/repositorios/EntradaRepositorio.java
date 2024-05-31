package com.cartelera.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cartelera.entidades.Entrada;

public interface EntradaRepositorio extends JpaRepository<Entrada, Long>{

    @Query("SELECT e.numeroAsiento FROM Entrada e WHERE e.sesionPelicula.id = :idSesion")
    List<Integer> obtenerAsientosOcupados(@Param("idSesion") Long idSesion);

    List<Entrada> findByUsuario_nombreUsuario(String nombreUsuario);
    
}
