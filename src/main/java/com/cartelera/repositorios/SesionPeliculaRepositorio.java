package com.cartelera.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cartelera.entidades.SesionPelicula;

@Repository
public interface SesionPeliculaRepositorio extends JpaRepository<SesionPelicula, Long>{

    @Query("SELECT sesion FROM SesionPelicula sesion WHERE sesion.fecha = :fecha AND ((sesion.horaInicio BETWEEN :horaInicio AND :horaFin) OR (sesion.horaFin BETWEEN :horaInicio AND :horaFin)) ")
    List<SesionPelicula> encuentraSesionPorFecha(@Param("fecha") LocalDate fecha, @Param("horaInicio") LocalTime horaInicio, @Param("horaFin") LocalTime horaFin);



}
