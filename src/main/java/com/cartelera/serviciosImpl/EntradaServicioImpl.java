package com.cartelera.serviciosImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartelera.entidades.Entrada;
import com.cartelera.repositorios.EntradaRepositorio;
import com.cartelera.servicios.EntradaServicio;

@Service
public class EntradaServicioImpl implements EntradaServicio{

    @Autowired
    EntradaRepositorio entradaRepositorio;

    @Override
    public List<Entrada> obtenerEntradasActuales(String nombreUsuario) {
       
        return this.entradaRepositorio.obtenerEntradaActuales(nombreUsuario);
    }


}
