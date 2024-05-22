package com.cartelera.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cartelera.clases.DetallePoster;
import com.cartelera.entidades.Pelicula;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long>{
    @Query("SELECT new com.cartelera.clases.DetallePoster(p.id, p.titulo, p.rutaPoster) FROM Pelicula p")
    List<DetallePoster> obtenerPostersPeliculas();

    @Query("SELECT new com.cartelera.clases.DetallePoster(p.id, p.titulo, p.rutaPoster) FROM Pelicula p WHERE p.fechaEstreno > '2024-01-01' ORDER BY p.promedioVotos DESC")
    List<DetallePoster> obtenerPostersPeliculasPopulares();

}
