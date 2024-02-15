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

import com.comercio.app.post.models.dao.PostDao;
import com.comercio.app.post.models.entity.Post;

@SpringBootTest
class PostServiceImplTest {

	@InjectMocks
	PostServiceImpl service;

	@Mock
	PostDao postDao;

	@Test
	void testListarPosts() {
		List<Post> posts = new ArrayList<Post>();
		Post post1 = new Post();
		post1.setText("Primer post");
		post1.setFechaPublicacion(new Date());
		post1.setId(1L);
		post1.setUsuarioId(1L);
		posts.add(post1);
		
		Post post2 = new Post();
		post2.setText("Segundo post");
		post2.setFechaPublicacion(new Date());
		post2.setId(2L);
		post2.setUsuarioId(1L);
		posts.add(post2);
		
		when(postDao.findAll()).thenReturn(posts);
		List<Post> response = service.listarPost();
		assertEquals(2, response.size());
		verify(postDao, times(1)).findAll();

	}
	
	@Test
	void testListarPostUsuario() {
		List<Post> posts = new ArrayList<Post>();
		Post post1 = new Post();
		post1.setText("Primer post");
		post1.setFechaPublicacion(new Date());
		post1.setId(1L);
		post1.setUsuarioId(1L);
		posts.add(post1);
		
		Post post2 = new Post();
		post2.setText("Segundo post");
		post2.setFechaPublicacion(new Date());
		post2.setId(2L);
		post2.setUsuarioId(1L);
		posts.add(post2);
		
		when(postDao.listarPostUsuario(1L)).thenReturn(posts);
		List<Post> response = service.listarPostUsuario(1L);
		assertEquals(2, response.size());
		verify(postDao, times(1)).listarPostUsuario(1L);

	}

	@Test
	void testcrearPost() {
		Post post = new Post();
		post.setText("Primer post");
		post.setId(1L);
		post.setUsuarioId(1L);

		service.crearPost(post);

		verify(postDao, times(1)).save(post);
	}

	@Test
	void testModificarPost() {
		Post post = new Post();
		post.setText("Primer post");
		post.setUsuarioId(1L);
		service.modificarPost(post);
		verify(postDao, times(1)).save(post);
	}

	@Test
	void testEliminarPost() {
		service.eliminarPost(1L);
		verify(postDao, times(1)).deleteById(1l);
	}

}
