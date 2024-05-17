package com.cartelera.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.Pelicula;
import com.cartelera.servicios.PeliculaServicio;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {
    @Autowired
    PeliculaServicio peliculaServicio;

    @PostMapping("/agregar")
    public ResponseEntity<Pelicula> agregarPeliculaABbdd(@RequestBody Pelicula pelicula){
        Pelicula nuevaPelicula= this.peliculaServicio.guardarPelicula(pelicula);
        if (nuevaPelicula != null) {
            return ResponseEntity.ok(nuevaPelicula);
        }
        return null;
    }

}
