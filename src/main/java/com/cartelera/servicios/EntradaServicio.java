package com.cartelera.servicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.cartelera.entidades.Entrada;

@Service
public interface EntradaServicio extends JpaRepository<Entrada, Long> {

}
