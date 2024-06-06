package com.cartelera.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cartelera.entidades.Entrada;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada, Long>{

    @Query("SELECT e.numeroAsiento FROM Entrada e WHERE e.sesionPelicula.id = :idSesion")
    List<Integer> obtenerAsientosOcupados(@Param("idSesion") Long idSesion);

    List<Entrada> findByUsuario_nombreUsuario(String nombreUsuario);
    
    @Query("SELECT e FROM Entrada e WHERE e.usuario.nombreUsuario = :nombre AND e.sesionPelicula.fecha >= :fechaActual AND e.sesionPelicula.horaInicio > :horaActual")
    List<Entrada> obtenerEntradaActuales(@Param("nombre") String nombre, @Param("fechaActual") LocalDate fechaActual, @Param("horaActual") LocalTime horaActual);
}
