package com.cartelera.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.SesionPelicula;
import com.cartelera.repositorios.SesionPeliculaRepositorio;
import com.cartelera.servicios.SesionPeliculaServicio;

@RestController
@RequestMapping("sesion_pelicula")
public class SesionPeliculaControlador {

    @Autowired
    SesionPeliculaServicio sesionPeliculaServicio;

    @Autowired
    SesionPeliculaRepositorio sesionPeliculaRepositorio;

    @PostMapping("/guardar")
    public ResponseEntity<SesionPelicula> guardarSesionPelicula(@RequestBody SesionPelicula sesionPelicula){
        if (sesionPeliculaServicio.sesionCoincide(sesionPelicula.getSala().getId(),sesionPelicula.getFecha(),sesionPelicula.getHoraInicio(), sesionPelicula.getPelicula().getDuracion())) {
            return new ResponseEntity("La sesion coincide con otra sesión", HttpStatus.BAD_REQUEST);
        }
        SesionPelicula sesionGuardada = sesionPeliculaServicio.guardarSesionPelicula(sesionPelicula);
        if (sesionGuardada != null) {
            return ResponseEntity.ok(sesionGuardada);
        }
        return new ResponseEntity("Ha ocurrido un error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/") 
    ResponseEntity<List<SesionPelicula>> obtenerSesiones(){
        return ResponseEntity.ok(sesionPeliculaRepositorio.findAll());
    }

}
