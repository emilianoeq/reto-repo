package com.comercio.app.post.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.comercio.app.post.models.entity.Post;

public interface PostDao extends CrudRepository<Post, Long> {

	@Query( value =  "SELECT p FROM Post p WHERE p.usuarioId =:idUser")
	List<Post> listarPostUsuario(@Param("idUser") Long idUser);

}
