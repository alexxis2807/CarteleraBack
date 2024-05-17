package com.cartelera.servicios;

import org.springframework.stereotype.Service;

import com.cartelera.entidades.Pelicula;

@Service
public interface PeliculaServicio {
    public Pelicula guardarPelicula(Pelicula pelicula);
}
