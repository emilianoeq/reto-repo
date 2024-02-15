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

import com.comercio.app.post.models.entity.Post;
import com.comercio.app.post.models.response.Response;
import com.comercio.app.post.models.service.IPostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private IPostService postService;

	@GetMapping("/listar")
	public List<Post> listar() {
		return postService.listarPost();
	}
	
	@GetMapping("/listar-usuario/{idUsuario}")
	public List<Post> listarUsuario(@PathVariable Long idUsuario) {
		return postService.listarPostUsuario(idUsuario);
	}

	@PostMapping("/crear")
	public ResponseEntity<Response> crear(@RequestBody Post post) {
		Post respuesta = postService.crearPost(post);
		Response response;
		if (respuesta != null) {
			response = new Response();
			response.setCodigo("0");
			response.setMensaje("Post creado");
			return ResponseEntity.ok().body(response);
		} else {
			response = new Response();
			response.setCodigo("-1");
			response.setMensaje("Error al crear post");
			return ResponseEntity.ok().body(response);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> eliminar(@PathVariable Long id) {
		postService.eliminarPost(id);
		Response response = new Response();
		response.setCodigo("0");
		response.setMensaje("Post eliminado");
		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<Response> modifcar(@RequestBody Post post, @PathVariable Long id) {
		Post postBd = postService.buscarPost(id);

		if (postBd != null) {
			if (postBd.getUsuarioId() != post.getUsuarioId()) {
				Response response = new Response();
				response.setCodigo("2");
				response.setMensaje("El post no puede ser modificado por el usuario");
				return ResponseEntity.ok().body(response);
			}
			postBd.setText(post.getText());
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.setTimeZone(TimeZone.getTimeZone("America/Lima"));
			date = calendar.getTime();

			postBd.setFechaModificacion(date);
			postService.modificarPost(postBd);

			Response response = new Response();
			response.setCodigo("0");
			response.setMensaje("Post modificado");
			return ResponseEntity.ok().body(response);
		} else {
			Response response = new Response();
			response.setCodigo("1");
			response.setMensaje("Post no existe");
			return ResponseEntity.ok().body(response);
		}

	}

}
