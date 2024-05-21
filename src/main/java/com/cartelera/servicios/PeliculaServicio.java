package com.cartelera.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cartelera.entidades.Pelicula;

@Service
public interface PeliculaServicio {
    public Pelicula guardarPelicula(Pelicula pelicula);
    public void eliminarPelicula(Long idPelicula);
    public List<Pelicula> obtenerTodasPeliculas();
    public Pelicula obtenerDetallesPelicula(Long idPelicula);
}
