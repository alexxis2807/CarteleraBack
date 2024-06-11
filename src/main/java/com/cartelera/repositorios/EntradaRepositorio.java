package com.cartelera.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cartelera.entidades.Entrada;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada, Long>{

    @Query("SELECT e.numeroAsiento FROM Entrada e WHERE e.sesionPelicula.id = :idSesion")
    List<Integer> obtenerAsientosOcupados(@Param("idSesion") Long idSesion);

    List<Entrada> findByUsuario_nombreUsuario(String nombreUsuario);
    
    @Query("SELECT e FROM Entrada e WHERE e.usuario.nombreUsuario = :nombre AND ( e.sesionPelicula.fecha > CURRENT_DATE OR (e.sesionPelicula.fecha = CURRENT_DATE AND e.sesionPelicula.horaInicio > CURRENT_TIME))")
    List<Entrada> obtenerEntradaActuales(@Param("nombre") String nombre);
    
    @Transactional
    void deleteByUsuario_idUsuario(Long idUsuario);
}
