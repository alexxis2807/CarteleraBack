package com.cartelera.entidades;

import java.util.List;

public class EntradaRequest {
    private Long idSesion;
    private List<Integer> asientos;
    private String nombreUsuario;
    private double precio;

    public Long getIdSesion() {
        return idSesion;
    }
    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }
    public List<Integer> getAsientos() {
        return asientos;
    }
    public void setAsientos(List<Integer> asientos) {
        this.asientos = asientos;
    }
    public String getnombreUsuario() {
        return nombreUsuario;
    }
    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
   
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
 
}
