package com.cartelera.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.clases.ConfirmaEntrada;
import com.cartelera.clases.EntradaRequest;
import com.cartelera.entidades.Entrada;
import com.cartelera.entidades.SesionPelicula;
import com.cartelera.entidades.Usuario;
import com.cartelera.repositorios.EntradaRepositorio;
import com.cartelera.repositorios.SesionPeliculaRepositorio;
import com.cartelera.repositorios.UsuarioRepositorio;
import com.cartelera.servicios.EntradaServicio;

@RestController
@RequestMapping("entrada")
public class EntradaControlador {
    @Autowired
    EntradaRepositorio entradaRepositorio;

    @Autowired
    EntradaServicio entradaServicio;

    @Autowired
    SesionPeliculaRepositorio sesionPeliculaRepositorio;
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @PostMapping("/guardar")
    public ResponseEntity<List<Entrada>> guardarEntradas(@RequestBody EntradaRequest entradaRequest){
        List<Integer> asientosOcupados = this.entradaRepositorio.obtenerAsientosOcupados(entradaRequest.getIdSesion());
        for (Integer asiento : entradaRequest.getAsientos()) {
            if (asientosOcupados.contains(asiento)) {
                return new ResponseEntity("Algún asiento de la sesión ya esta ocupado", HttpStatus.CONFLICT);
            }
        }
        List<Entrada> entradasGuardadas = new ArrayList<>();

        List<Integer> asientosAGuardar = entradaRequest.getAsientos();

        for (Integer asiento : asientosAGuardar) {
        Entrada entrada = new Entrada();
        SesionPelicula sesionPelicula = sesionPeliculaRepositorio.findById(entradaRequest.getIdSesion()).get();
        entrada.setSesionPelicula(sesionPelicula);;
        entrada.setNumeroAsiento(asiento);
        Usuario usuario = usuarioRepositorio.findByNombreUsuario(entradaRequest.getnombreUsuario());
        entrada.setUsuario(usuario);;
        entrada.setPrecio(entradaRequest.getPrecio());
        try {
            Entrada entradaGuardada = entradaRepositorio.save(entrada);
            entradasGuardadas.add(entradaGuardada);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity("El asiento de la sesión ya esta ocupado", HttpStatus.CONFLICT);
        }   

        }
            return ResponseEntity.ok(entradasGuardadas);
    }

    @PutMapping("/confirmarEntrada")
    public ResponseEntity<Entrada> confirmarEntrada(@RequestBody ConfirmaEntrada confirmaEntrada){
        Long idEntrada = confirmaEntrada.getIdEntrada();
        String nombreUsuario = confirmaEntrada.getNombreUsuario();

        Optional<Entrada> optionalEntrada = entradaRepositorio.findById(idEntrada);
        if (optionalEntrada.isPresent()) {
            Entrada entrada = optionalEntrada.get();
            Usuario usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario);
            entrada.setUsuario(usuario);
            return ResponseEntity.ok(entradaRepositorio.save(entrada));
            
        }
        return new ResponseEntity("No existe la entrada", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/nombreUsuario/{nombreUsuario}")
    public ResponseEntity<List<Entrada>> obtenerEntradasActualesUsuario (@PathVariable("nombreUsuario") String nombreUsuario){
        return ResponseEntity.ok(this.entradaServicio.obtenerEntradasActuales(nombreUsuario));
    }

    @GetMapping("/todas/nombreUsuario/{nombreUsuario}")
    public ResponseEntity<List<Entrada>> obtenerEntradasUsuario (@PathVariable("nombreUsuario") String nombreUsuario){
        return ResponseEntity.ok(this.entradaRepositorio.findByUsuario_nombreUsuario(nombreUsuario));
    }

    @DeleteMapping("/eliminar/{idEntrada}")
    public void eliminarEntradaPorId(@PathVariable("idEntrada") Long idEntrada){
        this.entradaRepositorio.deleteById(idEntrada);
    }

    
}
