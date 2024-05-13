package com.cartelera.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private String pelicula;
    private LocalDateTime fechaHora;
    private Long cantidad;
    private double precio;
    private LocalDateTime fechaCompra;
    
    public Entrada(Usuario usuario, String pelicula, LocalDateTime fechaHora, Long cantidad, double precio) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.fechaHora = fechaHora;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCompra = LocalDateTime.now();
    }


    public Entrada() {
    }


    public Long getId_entrada() {
        return id_entrada;
    }
    public void setId_entrada(Long id_entrada) {
        this.id_entrada = id_entrada;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getPelicula() {
        return pelicula;
    }
    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    
}
