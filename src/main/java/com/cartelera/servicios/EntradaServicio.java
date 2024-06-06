package com.cartelera.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cartelera.entidades.Entrada;

@Service
public interface EntradaServicio {
    List<Entrada> obtenerEntradasActuales(String nombreUsuario);
}
