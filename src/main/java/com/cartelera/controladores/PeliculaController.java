package com.cartelera.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.Pelicula;
import com.cartelera.request.PeliculaRequest;
import com.cartelera.servicios.PeliculaServicio;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {
    @Autowired
    PeliculaServicio peliculaServicio;

    @PostMapping("/agregar")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Pelicula> agregarPeliculaABbdd(@RequestBody PeliculaRequest pelicula){
        Pelicula peliculaAGuardar = new Pelicula(pelicula);
        Pelicula nuevaPelicula= this.peliculaServicio.guardarPelicula(peliculaAGuardar);
        if (nuevaPelicula != null) {
            return ResponseEntity.ok(nuevaPelicula);
        }
        return new ResponseEntity("No se ha podido guardar la pelicula", HttpStatus.BAD_REQUEST);
    }
}
