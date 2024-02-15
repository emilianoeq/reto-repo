package com.comercio.app.post.models.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comercio.app.post.models.dao.PostDao;
import com.comercio.app.post.models.entity.Post;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostDao postDao;

	@Override
	@Transactional
	public Post crearPost(Post post) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		date = calendar.getTime();
		post.setFechaPublicacion(date);
		return postDao.save(post);
	}

	@Override
	@Transactional
	public Post modificarPost(Post post) {
		return postDao.save(post);
	}

	@Override
	@Transactional
	public void eliminarPost(Long id) {
		postDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> listarPost() {
		return (List<Post>) postDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Post buscarPost(Long id) {
		return postDao.findById(id).orElse(null);
	}

	@Override
	public List<Post> listarPostUsuario(Long idUsuario) {
		return postDao.listarPostUsuario(idUsuario);
	}

}
