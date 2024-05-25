package com.cartelera.servicios;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.cartelera.entidades.SesionPelicula;

@Service
public interface SesionPeliculaServicio {
    public boolean sesionCoincide(Long idSala, LocalDate fecha, LocalTime hora, int duracionPelicula);
    public SesionPelicula guardarSesionPelicula(SesionPelicula sesion);

}
