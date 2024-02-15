package com.comercio.app.post.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.comercio.app.post.models.entity.Usuario;



public interface UsuarioDao extends CrudRepository<Usuario, Long> {

}
