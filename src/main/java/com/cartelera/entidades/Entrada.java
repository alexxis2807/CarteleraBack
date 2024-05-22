package com.cartelera.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sesion_pelicula_id", nullable = false)
    private SesionPelicula sesionPelicula;

    @Column(name = "numero_asiento")
    private int numeroAsiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Entrada() {
    }

    public Entrada(SesionPelicula sesionPelicula, int numeroAsiento, Usuario usuario) {
        this.sesionPelicula = sesionPelicula;
        this.numeroAsiento = numeroAsiento;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SesionPelicula getSesionPelicula() {
        return sesionPelicula;
    }

    public void setSesionPelicula(SesionPelicula sesionPelicula) {
        this.sesionPelicula = sesionPelicula;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
