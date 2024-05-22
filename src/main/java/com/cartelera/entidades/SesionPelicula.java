package com.cartelera.entidades;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "sesion_pelicula", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sala_id", "fecha", "hora"})
})
public class SesionPelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;


    
    public SesionPelicula() {
    }



    public SesionPelicula(Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime hora) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Pelicula getPelicula() {
        return pelicula;
    }



    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }



    public Sala getSala() {
        return sala;
    }



    public void setSala(Sala sala) {
        this.sala = sala;
    }



    public LocalDate getFecha() {
        return fecha;
    }



    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }



    public LocalTime getHora() {
        return hora;
    }



    public void setHora(LocalTime hora) {
        this.hora = hora;
    }



}