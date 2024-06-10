package com.cartelera.clases;

public class ConfirmaEntrada {
    private Long idEntrada;
    private String nombreUsuario;
    public ConfirmaEntrada(Long idEntrada, String nombreUsuario) {
        this.idEntrada = idEntrada;
        this.nombreUsuario = nombreUsuario;
    }
    public ConfirmaEntrada() {
    }
    public Long getIdEntrada() {
        return idEntrada;
    }
    public void setIdEntrada(Long idEntrada) {
        this.idEntrada = idEntrada;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
}
