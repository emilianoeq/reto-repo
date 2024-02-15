package com.comercio.app.post.models.service;

import java.util.List;

import com.comercio.app.post.models.entity.Post;

public interface IPostService {
	public Post crearPost(Post post);

	public Post modificarPost(Post post);

	public void eliminarPost(Long id);

	public List<Post> listarPost();

	public Post buscarPost(Long id);

	public List<Post> listarPostUsuario(Long id);

}
