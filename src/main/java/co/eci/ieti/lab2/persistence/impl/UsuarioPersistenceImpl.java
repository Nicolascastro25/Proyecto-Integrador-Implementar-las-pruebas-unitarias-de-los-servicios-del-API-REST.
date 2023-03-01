package co.eci.ieti.lab2.persistence.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Component;

import co.eci.ieti.lab2.model.Usuario;
import co.eci.ieti.lab2.persistence.UsuarioPersistence;

@Component
public class UsuarioPersistenceImpl implements UsuarioPersistence{

    private final Map<Integer, Usuario> usuarios = new HashMap<>();

    public UsuarioPersistenceImpl(){

        List<String> nombres = List.of("Juan", "Pedro", "Maria", "Jose", "Luisa", "Camila", "Andres", "Sofia", "Julian", "Laura");
        List<String> apellidos = List.of("Perez", "Gomez", "Gonzalez", "Rodriguez", "Lopez", "Martinez", "Hernandez", "Diaz", "Gutierrez", "Ramirez");

        for(int i = 0; i < 10; i++){
            Usuario usuario = new Usuario(i,nombres.get(i), apellidos.get(i), nombres.get(i).toLowerCase() + "@gmail.com", "123456");
            usuarios.put(i, usuario);
        }
    }

    public boolean existsById(int id){
        return usuarios.containsKey(id);
    }
    
    public List<Usuario> findAll(){
        return List.copyOf(usuarios.values());
    }

    public void save(Usuario usuario){
        if(usuarios.containsKey(usuario.getId())){
            usuario.setId(usuarios.size());
        }
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario findById(int id){
        return usuarios.get(id);
    }

    public Usuario findByEmail(String email){
        for(Usuario usuario : usuarios.values()){
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }
        return null;
    }

    public void update(int id, Usuario usuario){
        Usuario temp = findById(id);
        temp.setId(usuario.getId());
        temp.setNombre(usuario.getNombre());
        temp.setApellido(usuario.getApellido());
        temp.setEmail(usuario.getEmail());
        temp.setPassword(usuario.getPassword());

        usuarios.replace(usuario.getId(), temp);

    }

    public void delete(int id){
        usuarios.remove(id);
    }

}
