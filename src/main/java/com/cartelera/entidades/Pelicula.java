package com.cartelera.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.Set;

import com.cartelera.request.PeliculaRequest;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    private Long id;

    private boolean adulto;

    @Column(name = "ruta_fondo")
    private String rutaFondo;

    @ElementCollection
    private Set<Genero> generos;

    @Column(name = "idioma_original")
    private String idiomaOriginal;

    @Column(name = "titulo_original")
    private String tituloOriginal;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    private String estado;

    private Integer duracion;

    private double popularidad;

    @Column(name = "ruta_poster")
    private String rutaPoster;

    @Column(name = "fecha_estreno")
    private String fechaEstreno;

    @Column(length = 255)
    private String titulo;

    private boolean video;

    @Column(name = "promedio_votos")
    private double promedioVotos;

    @Column(name = "votos_totales")
    private int votosTotales;

    @Column(name = "ruta_trailer")
    private String rutaTrailer;

    public Pelicula() {}


    public Pelicula(PeliculaRequest peliculaApi) {
        this.id = peliculaApi.getId();
        this.adulto = peliculaApi.isAdult();
        this.rutaFondo = peliculaApi.getBackdrop_path();
        this.generos = peliculaApi.getGenres();
        this.idiomaOriginal = peliculaApi.getOriginal_language();
        this.tituloOriginal = peliculaApi.getOriginal_title();
        this.resumen = peliculaApi.getOverview();
        this.estado = peliculaApi.getStatus();
        this.duracion = peliculaApi.getRuntime();
        this.popularidad = peliculaApi.getPopularity();
        this.rutaPoster = peliculaApi.getPoster_path();
        this.fechaEstreno = peliculaApi.getRelease_date();
        this.titulo = peliculaApi.getTitle();
        this.video = peliculaApi.isVideo();
        this.promedioVotos = peliculaApi.getVote_average();
        this.votosTotales = peliculaApi.getVote_count();
        this.rutaTrailer = peliculaApi.getTrailer_path();
    }

    public Pelicula(Long id, boolean adulto, String rutaFondo, Set<Genero> generos, String idiomaOriginal, String tituloOriginal,
            String resumen, String estado, Integer duracion, double popularidad, String rutaPoster, String fechaEstreno,
            String titulo, boolean video, double promedioVotos, int votosTotales, String rutaTrailer) {
        this.id = id;
        this.adulto = adulto;
        this.rutaFondo = rutaFondo;
        this.generos = generos;
        this.idiomaOriginal = idiomaOriginal;
        this.tituloOriginal = tituloOriginal;
        this.resumen = resumen;
        this.estado = estado;
        this.duracion = duracion;
        this.popularidad = popularidad;
        this.rutaPoster = rutaPoster;
        this.fechaEstreno = fechaEstreno;
        this.titulo = titulo;
        this.video = video;
        this.promedioVotos = promedioVotos;
        this.votosTotales = votosTotales;
        this.rutaTrailer = rutaTrailer;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public Integer getDuracion() {
        return duracion;
    }



    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }



    public int getVotosTotales() {
        return votosTotales;
    }



    public void setVotosTotales(int votosTotales) {
        this.votosTotales = votosTotales;
    }



    public boolean isAdulto() {
        return adulto;
    }

    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    public String getRutaFondo() {
        return rutaFondo;
    }

    public void setRutaFondo(String rutaFondo) {
        this.rutaFondo = rutaFondo;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public double getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(double popularidad) {
        this.popularidad = popularidad;
    }

    public String getRutaPoster() {
        return rutaPoster;
    }

    public void setRutaPoster(String rutaPoster) {
        this.rutaPoster = rutaPoster;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getPromedioVotos() {
        return promedioVotos;
    }

    public void setPromedioVotos(double promedioVotos) {
        this.promedioVotos = promedioVotos;
    }

    public String getRutaTrailer() {
        return rutaTrailer;
    }

    public void setRutaTrailer(String rutaTrailer) {
        this.rutaTrailer = rutaTrailer;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


}
