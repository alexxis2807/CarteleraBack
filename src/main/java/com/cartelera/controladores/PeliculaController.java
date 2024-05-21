package com.cartelera.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.clases.DetallePoster;
import com.cartelera.entidades.Pelicula;
import com.cartelera.repositorios.PeliculaRepositorio;
import com.cartelera.request.PeliculaRequest;
import com.cartelera.servicios.PeliculaServicio;

import jakarta.persistence.Tuple;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {
    @Autowired
    PeliculaServicio peliculaServicio;

    @Autowired
    PeliculaRepositorio peliculaRepositorio;

    @PostMapping("/agregar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Pelicula> agregarPeliculaABbdd(@RequestBody PeliculaRequest pelicula){
        if (this.peliculaRepositorio.existsById(pelicula.getId())) {
            return new ResponseEntity("El id ya existe", HttpStatus.BAD_REQUEST);
        }
        Pelicula peliculaAGuardar = new Pelicula(pelicula);
        Pelicula nuevaPelicula= this.peliculaServicio.guardarPelicula(peliculaAGuardar);
        if (nuevaPelicula != null) {
            return ResponseEntity.ok(nuevaPelicula);
        }
        return new ResponseEntity("No se ha podido guardar la pelicula", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{idPelicula}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable("idPelicula") Long idPelicula){
        if(this.peliculaRepositorio.existsById(idPelicula)){
            this.peliculaServicio.eliminarPelicula(idPelicula);
            return new ResponseEntity("Eliminado con exito", HttpStatus.OK);
        }else{
            return new ResponseEntity("El id no existe", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detalles")
    public ResponseEntity<List<Pelicula>> obtenerTodasPeliculas(){
        List<Pelicula> peliculas = this.peliculaServicio.obtenerTodasPeliculas();
        if (peliculas.size() > 0) {
            return ResponseEntity.ok(peliculas);
        }
        return new ResponseEntity("No se encontraron peliculas", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detalles/{idPelicula}")
    public ResponseEntity<Pelicula> obtenerDetallesPelicula (@PathVariable("idPelicula") Long idPelicula){
        Pelicula pelicula = this.peliculaServicio.obtenerDetallesPelicula(idPelicula);
        if (pelicula != null) {
            return ResponseEntity.ok(pelicula);
        }
        return new ResponseEntity("No se ha encontrado la pelicula", HttpStatus.NOT_FOUND);
}

    @GetMapping("/posters")
    public ResponseEntity<List<DetallePoster>> obtenerDetallesPosterPeliculas (){
        return ResponseEntity.ok(this.peliculaRepositorio.obtenerPostersPeliculas());
}
}
