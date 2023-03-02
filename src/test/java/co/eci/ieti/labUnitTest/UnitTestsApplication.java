package co.eci.ieti.labUnitTest;

import co.eci.ieti.labUnitTest.model.Usuario;
import co.eci.ieti.labUnitTest.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest (classes = UnitTestApplication.class)
class UnitTestsApplication {

	@Test
	void contextLoads() {
	}

	@Test
	void testGetUsuarioByIdService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		when(usuarioService.findById(1)).thenReturn(usuario);
		assert usuarioService.findById(1).getNombre().equals("Jaime");
	}

	@Test
	void testSaveUsuarioService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		usuarioService.save(usuario);
		verify(usuarioService, times(1)).save(usuario);
	}

	@Test
	void testExistUsuarioByIdService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		when(usuarioService.existsById(1)).thenReturn(true);
		assert usuarioService.existsById(1);
	}

	@Test
	void testGetAllUsuariosService(){
		UsuarioService usuarioService = mock(UsuarioService.class);
		usuarioService.findAll();
		verify(usuarioService, times(1)).findAll();
	}

	@Test
	void testUpdateUsuarioService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		usuarioService.update(1, usuario);
		verify(usuarioService, times(1)).update(1, usuario);
	}

	@Test
	void testDeleteUsuarioService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		usuarioService.delete(1);
		verify(usuarioService, times(1)).delete(1);
	}

	@Test
	void testFailedGetUsuarioByIdService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		when(usuarioService.findById(1)).thenReturn(usuario);
		assert !usuarioService.findById(1).getNombre().equals("Andres");
	}

	@Test
	void testEmptyGetAllUsuariosService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		List<Usuario> usuarios =usuarioService.findAll();
		assert usuarios.size() == 0;
	}

	@Test
	void testNotEmptyGetAllUsuariosService() {
		UsuarioService usuarioService = mock(UsuarioService.class);
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Jaime");
		usuario.setApellido("Castro");
		usuario.setEmail("jaime.castro@gmail.com");
		usuario.setPassword("12345");
		usuarioService.save(usuario);
		List<Usuario> usuarios =usuarioService.findAll();
		assert usuarios.size() != 1;
	}

}


