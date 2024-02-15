package com.comercio.app.post.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.app.post.models.entity.Usuario;
import com.comercio.app.post.models.response.Response;
import com.comercio.app.post.models.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/listar")
	public List<Usuario> listar() {
		List<Usuario> usas = usuarioService.listarUsuarios();

		for (Usuario usuario : usas) {

		}

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		date = calendar.getTime();

		return usas;
	}

	@PostMapping("/crear")
	public ResponseEntity<Response> crear(@RequestBody Usuario usuario) {
		Usuario respuesta = usuarioService.crearUsuario(usuario);
		Response response;
		if (respuesta != null) {
			response = new Response();
			response.setCodigo("0");
			response.setMensaje("Usuario creado");
			return ResponseEntity.ok().body(response);
		} else {
			response = new Response();
			response.setCodigo("-1");
			response.setMensaje("Error al crear usuario");
			return ResponseEntity.ok().body(response);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> eliminar(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
		Response response = new Response();
		response.setCodigo("0");
		response.setMensaje("Usuario eliminado");
		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<Response> modifcar(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario usuarioBd = usuarioService.buscarUsuario(id);

		// ZoneId zoneId = ZoneId.of( "America/Lima" );
		// ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );

		if (usuarioBd != null) {
			usuarioBd.setCellPhone(usuario.getCellPhone());
			usuarioBd.setLastName(usuario.getLastName());
			usuarioBd.setName(usuario.getName());
			usuarioBd.setPassword(usuario.getPassword());

			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.setTimeZone(TimeZone.getTimeZone("America/Lima"));
			date = calendar.getTime();

			usuarioBd.setFechaModificacion(date);
			usuarioService.modificarUsuario(usuarioBd);

			Response response = new Response();
			response.setCodigo("0");
			response.setMensaje("Usuario modificado");
			return ResponseEntity.ok().body(response);
		} else {
			Response response = new Response();
			response.setCodigo("1");
			response.setMensaje("Usuario no existe");
			return ResponseEntity.ok().body(response);
		}

	}

}
