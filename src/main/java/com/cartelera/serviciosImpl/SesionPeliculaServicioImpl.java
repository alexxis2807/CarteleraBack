package com.cartelera.serviciosImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartelera.entidades.SesionPelicula;
import com.cartelera.repositorios.SesionPeliculaRepositorio;
import com.cartelera.servicios.SesionPeliculaServicio;

@Service
public class SesionPeliculaServicioImpl implements SesionPeliculaServicio{
    @Autowired
    SesionPeliculaRepositorio sesionPeliculaRepositorio;

    @Override
    public boolean sesionCoincide(Long idSala, LocalDate fecha, LocalTime hora, int duracionPelicula){
        LocalTime horaInicio = hora;
        LocalTime horaFin = hora.plus(Duration.ofMinutes(duracionPelicula));

        List<SesionPelicula> sesiones = sesionPeliculaRepositorio.encuentraSesionPorFecha(fecha, horaInicio, horaFin);
        return !sesiones.isEmpty();
    }

    @Override
    public SesionPelicula guardarSesionPelicula(SesionPelicula sesion){
        return this.sesionPeliculaRepositorio.save(sesion);
    }
}
