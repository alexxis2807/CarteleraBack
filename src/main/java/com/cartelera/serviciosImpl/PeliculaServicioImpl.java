package com.cartelera.serviciosImpl;

import java.util.List;
import java.util.Optional;

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
        if (pelicula.getDuracion() > 0) {
            return this.peliculaRepositorio.save(pelicula);
        }
        return null;
    }
    @Override
    public void eliminarPelicula(Long idPelicula) {
         this.peliculaRepositorio.deleteById(idPelicula);
    }
    @Override
    public List<Pelicula> obtenerTodasPeliculas() {
        return this.peliculaRepositorio.findAll();
    }
    @Override
    public Pelicula obtenerDetallesPelicula(Long idPelicula) {
        Optional<Pelicula> pelicula =  this.peliculaRepositorio.findById(idPelicula);
        if(pelicula.isPresent()){
            return pelicula.get();
        }
        return null;
    }

}
