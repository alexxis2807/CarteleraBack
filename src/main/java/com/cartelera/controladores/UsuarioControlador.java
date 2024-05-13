package com.cartelera.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartelera.entidades.Usuario;
import com.cartelera.servicios.UsuarioServicio;

@RestController
@RequestMapping("usuarios")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Value("${app.secret.key}")
    private String secretKey;

    @GetMapping("/busca/{id_usuario}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id_usuario") Long id_usuario){
        if (id_usuario == null) {
            return new ResponseEntity("Debe introducir un id de usuario", HttpStatus.BAD_REQUEST);
        }
        try {
            Usuario usuario = this.usuarioServicio.buscaPorIdUsuario(id_usuario);   
            return  usuario != null ? ResponseEntity.ok(usuario) : new ResponseEntity("No se ha encontrado", HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            return new ResponseEntity("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios(){
        try {            
            List<Usuario> listaUsuarios = this.usuarioServicio.buscaTodosUsuarios();
            return ResponseEntity.ok(listaUsuarios);
        } catch (DataAccessException ex) {
            return new ResponseEntity("Error al conectar con la base de datos",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registrar")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario){
        if(usuario.getNombreUsuario() == null)
            return  new ResponseEntity("El campo nombreUsuario es obligatorio", HttpStatus.BAD_REQUEST);
        if(usuario.getContraseña() == null)
            return  new ResponseEntity("El campo contraseña es obligatorio", HttpStatus.BAD_REQUEST);
        if(usuario.getCorreo() == null)
            return  new ResponseEntity("El campo correo es obligatorio", HttpStatus.BAD_REQUEST);
        
        try {
            if (this.usuarioServicio.existeNombreUsuario(usuario.getNombreUsuario())) {
                return  new ResponseEntity("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
            }
            if (this.usuarioServicio.existeCorreo(usuario.getCorreo())) {
                return  new ResponseEntity("Ese correo ya está registrado", HttpStatus.BAD_REQUEST);
            }
            
            Usuario usuarioRegistrado = this.usuarioServicio.registrarUser(usuario);
            return ResponseEntity.ok(usuarioRegistrado);

        }catch (DataAccessException ex) {
            return new ResponseEntity("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/eliminar/{id_usuario}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id_usuario") Long id_usuario){
        if (id_usuario == null) {
            return new ResponseEntity("Debe introducir un id de usuario", HttpStatus.BAD_REQUEST);
        }
        try {
            Usuario usuario = this.usuarioServicio.buscaPorIdUsuario(id_usuario);
            if(usuario == null){
                return new ResponseEntity("No existe ese usuario",HttpStatus.NOT_FOUND);
            }
            this.usuarioServicio.eliminarUsuario(id_usuario);
            return ResponseEntity.ok(usuario);
        } catch (DataAccessException e) {
            return new ResponseEntity("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/busca/nombre/{nombreUsuario}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombre(@PathVariable("nombreUsuario") String nombreUsuario){
        if (nombreUsuario == null) {
            return new ResponseEntity("Debe introducir un nombre de usuario", HttpStatus.BAD_REQUEST);
        }
        try {
            Usuario usuario = this.usuarioServicio.buscaUsuarioPorNombre(nombreUsuario);   
            return  usuario != null ? ResponseEntity.ok(usuario) : new ResponseEntity("No se ha encontrado", HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            return new ResponseEntity("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inicioSesion/{nombreUsuario}/{contraseña}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Usuario> inicioSesion(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("contraseña") String contraseña){

        if (nombreUsuario == null || contraseña == null) {
            return new ResponseEntity("Falta algun dato obligatorio (nombreUsuario, contraseña)", HttpStatus.BAD_REQUEST);
        }
        if(!this.usuarioServicio.existeNombreUsuario(nombreUsuario)){
            return new ResponseEntity("No existe ese nombre de usuario", HttpStatus.NOT_FOUND);
        }

        try {
            Usuario usuario = this.usuarioServicio.inicioSesion(nombreUsuario, contraseña);
    
            if(usuario == null){
                return new ResponseEntity("La contraseña es incorrecta", HttpStatus.NOT_FOUND);
            }
    
            return ResponseEntity.ok(usuario);
        } catch (DataAccessException e) {
            return new ResponseEntity("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
