package com.comercio.app.post.models.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.comercio.app.post.models.dao.UsuarioDao;
import com.comercio.app.post.models.entity.Usuario;

@SpringBootTest
class UsuarioServiceImplTest {

	@InjectMocks
	UsuarioServiceImpl service;

	@Mock
	UsuarioDao usuarioDao;

	@Test
	void testListarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario();
		usuario1.setCellPhone("123456789");
		usuario1.setFechaAlta(new Date());
		usuario1.setFechaModificacion(new Date());
		usuario1.setId(1L);
		usuario1.setLastName("Gomez Prado");
		usuario1.setName("Mario");
		usuario1.setPassword("123456");
		usuarios.add(usuario1);

		Usuario usuario2 = new Usuario();
		usuario1.setCellPhone("993456789");
		usuario1.setFechaAlta(new Date());
		usuario1.setFechaModificacion(new Date());
		usuario1.setId(2L);
		usuario1.setLastName("Romero Quispe");
		usuario1.setName("Rosa");
		usuario1.setPassword("123456");
		usuarios.add(usuario2);

		Usuario usuario3 = new Usuario();
		usuario1.setCellPhone("993456788");
		usuario1.setFechaAlta(new Date());
		usuario1.setFechaModificacion(new Date());
		usuario1.setId(3L);
		usuario1.setLastName("Gomez Martines");
		usuario1.setName("Bertha");
		usuario1.setPassword("123456");
		usuarios.add(usuario3);

		when(usuarioDao.findAll()).thenReturn(usuarios);

		List<Usuario> response = service.listarUsuarios();

		assertEquals(3, response.size());
		verify(usuarioDao, times(1)).findAll();

	}

	@Test
	void testCrearUsuario() {
		Usuario usuario = new Usuario();
		usuario.setCellPhone("123456789");
		usuario.setId(1L);
		usuario.setLastName("Gomez Prado");
		usuario.setName("Mario");
		usuario.setPassword("123456");

		service.crearUsuario(usuario);

		verify(usuarioDao, times(1)).save(usuario);
	}

	@Test
	void testModificarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setCellPhone("123456789");
		usuario.setId(1L);
		usuario.setLastName("Gomez Prado");
		usuario.setName("Mario");
		usuario.setPassword("123456");

		service.modificarUsuario(usuario);

		verify(usuarioDao, times(1)).save(usuario);
	}

	@Test
	void testEliminarUsuario() {
		service.eliminarUsuario(1L);
		verify(usuarioDao, times(1)).deleteById(1l);
	}

}
