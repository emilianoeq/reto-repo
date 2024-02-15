package com.comercio.app.post.models.service;

import java.util.List;

import com.comercio.app.post.models.entity.Usuario;



public interface IUsuarioService {
	public Usuario crearUsuario(Usuario usuario);
	public Usuario modificarUsuario(Usuario usuario);
	public void eliminarUsuario(Long id);
	public List<Usuario> listarUsuarios();
	public Usuario buscarUsuario(Long id);
	
	

}
