package com.cartelera.controladores;

import java.util.List;

public class EntradaRequest {
    private Long idSesion;
    private List<Integer> asientos;
    private Long idUsuario;
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
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
 
}
