package com.cartelera.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.Sala;
import com.cartelera.repositorios.SalaRepositorio;

@RestController
@RequestMapping("sala")
public class SalaControlador {

    @Autowired
    SalaRepositorio salaRepositorio;

    @GetMapping("/{idSala}")
    public ResponseEntity<Sala> obtenerSala(@PathVariable("idSala") Long idSala){
        Optional<Sala> sala = this.salaRepositorio.findById(idSala);

        if (sala.isPresent()) {
            return ResponseEntity.ok(sala.get());
        }

        return new ResponseEntity("No se ha encontrado ninguna sala", HttpStatus.NOT_FOUND);
    }

}
