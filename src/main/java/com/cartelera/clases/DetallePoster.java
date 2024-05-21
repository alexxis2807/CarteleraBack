package com.cartelera.clases;

public class DetallePoster {
    private Long id;
    private String titulo;
    private String rutaPoster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaPoster() {
        return rutaPoster;
    }

    public void setRutaPoster(String rutaPoster) {
        this.rutaPoster = rutaPoster;
    }

    public DetallePoster() {
    }

    public DetallePoster(Long id, String titulo, String rutaPoster) {
        this.id = id;
        this.titulo = titulo;
        this.rutaPoster = rutaPoster;
    }

    
}