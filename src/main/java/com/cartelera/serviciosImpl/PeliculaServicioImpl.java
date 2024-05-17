package com.cartelera.serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartelera.entidades.Pelicula;
import com.cartelera.repositorios.PeliculaRepositorio;
import com.cartelera.servicios.PeliculaServicio;

@Service
public class PeliculaServicioImpl implements PeliculaServicio{

    @Autowired
    PeliculaRepositorio peliculaRepositorio;
    @Override
    public Pelicula guardarPelicula(Pelicula pelicula) {
        return this.peliculaRepositorio.save(pelicula);
    }

}
