package co.eci.ieti.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.eci.ieti.lab2.persistence.UsuarioPersistence;
import java.util.List;

import co.eci.ieti.lab2.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioPersistence usuarioPersistence;

    public boolean existsById(int id){
        return usuarioPersistence.existsById(id);
    }

    public List<Usuario> findAll() {
        return usuarioPersistence.findAll();
    }

    public void save(Usuario usuario){
        usuarioPersistence.save(usuario);
    }

    public Usuario findById(int id){
        return usuarioPersistence.findById(id);
    }

    public Usuario findByEmail(String email){
        return usuarioPersistence.findByEmail(email);
    }

    public void update(int id,Usuario usuario){
        usuarioPersistence.update(id,usuario);
    }

    public void delete(int id){
        usuarioPersistence.delete(id);
    }

}
