package com.cartelera.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartelera.entidades.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long>{
    
}
