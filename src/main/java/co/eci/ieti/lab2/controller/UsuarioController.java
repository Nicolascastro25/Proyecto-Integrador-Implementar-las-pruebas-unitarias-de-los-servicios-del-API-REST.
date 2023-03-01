package co.eci.ieti.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.List;
import java.util.Date;

import co.eci.ieti.lab2.controller.auth.LoginDto;
import co.eci.ieti.lab2.controller.auth.TokenDto;
import co.eci.ieti.lab2.model.Usuario;

import co.eci.ieti.lab2.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.security.RolesAllowed;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Value("${jwt.secret}")
    String secret;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto){

        Usuario usuario = usuarioService.findByEmail(loginDto.email);
        if(BCrypt.checkpw(loginDto.password, usuario.getPassword())){
            return generateTokenDto(usuario);
        }else{
            return null;
        }
    }

    private String generateToken(Usuario usuario, Date expirationDate){
        return Jwts.builder()
        .setSubject(usuario.getEmail())
        .claim("role", "USER")
        .setIssuedAt(new Date())
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    }

    private TokenDto generateTokenDto(Usuario usuario){
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, 30);
        return new TokenDto(generateToken(usuario,expirationDate.getTime()), expirationDate.getTime());
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return new ResponseEntity<List<Usuario>>(usuarioService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") int id){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuarioService.findById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addUsuario")
    @RolesAllowed("USER")
    public ResponseEntity<?> addUsuario(Usuario usuario){
        try{
            usuarioService.save(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/updateUsuario/{id}")
    @RolesAllowed("USER")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") int id,Usuario usuario){
        if(usuarioService.existsById(id)){
            try{
                usuarioService.update(id,usuario);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUsuario/{id}")
    @RolesAllowed("USER")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id){
        if(usuarioService.existsById(id)){
            try{
                usuarioService.delete(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
