package com.cartelera.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.SesionPelicula;
import com.cartelera.repositorios.EntradaRepositorio;
import com.cartelera.repositorios.SesionPeliculaRepositorio;
import com.cartelera.servicios.SesionPeliculaServicio;

@RestController
@RequestMapping("sesion_pelicula")
public class SesionPeliculaControlador {
    @Autowired
    EntradaRepositorio entradaRepositorio;

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

    @GetMapping("/fecha/{fecha}/id/{idPelicula}") 
    ResponseEntity<List<SesionPelicula>> obtenerSesionesPeliculaFecha(@PathVariable("fecha") LocalDate fecha, @PathVariable("idPelicula") Long idPelicula){
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        return ResponseEntity.ok(this.sesionPeliculaRepositorio.encuentraSesionesFechaPelicula(fecha, idPelicula, fechaActual, horaActual));
    }

    @GetMapping("/fechas/id/{idPelicula}") 
    ResponseEntity<Set<LocalDate>> obtenerFechasSesionesPelicula(@PathVariable("idPelicula") Long idPelicula){
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        return ResponseEntity.ok(this.sesionPeliculaRepositorio.encuentraFechasSesionesPelicula(idPelicula, fechaActual, horaActual));
    }

    @GetMapping("/id/{idSesion}")
    ResponseEntity<SesionPelicula> obtenerSesionPorId(@PathVariable("idSesion") Long idSesion){
        Optional<SesionPelicula> sesion = this.sesionPeliculaRepositorio.findById(idSesion);
        if (sesion.isPresent()) {
            return ResponseEntity.ok(sesion.get());
        }
        return new ResponseEntity("No se ha encontrado la sala.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/asientos/idSesion/{idSesion}")
    public ResponseEntity<List<Integer>> obtenerAsientosOcupadosSala(@PathVariable("idSesion") Long idSesion){
        return ResponseEntity.ok(this.entradaRepositorio.obtenerAsientosOcupados(idSesion));
    }
}
 