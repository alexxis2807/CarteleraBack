package com.cartelera.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cartelera.entidades.Entrada;

public interface EntradaRepositorio extends JpaRepository<Entrada, Long>{

    @Query("SELECT e.numeroAsiento FROM Entrada e WHERE e.sesionPelicula.id = :idSesion")
    Integer[] obtenerAsientosOcupados(@Param("idSesion") Long idSesion);
}
