package com.comercio.app.post.models.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comercio.app.post.models.dao.UsuarioDao;
import com.comercio.app.post.models.entity.Usuario;



@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional
	public Usuario crearUsuario(Usuario usuario) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		date = calendar.getTime();
		usuario.setFechaAlta(date);
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public Usuario modificarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(Long id) {
		return  usuarioDao.findById(id).orElse(null);
	}

}
