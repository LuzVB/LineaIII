package cundi.edu.co.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.UsuarioDto;
import cundi.edu.co.demo.entity.Usuario;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IUsuarioService;

@Service
@Qualifier("usuario2")
public class UsuarioServiceImpl2 implements IUsuarioService {

	@Override
	public List<UsuarioDto> retornar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDto obtener(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pruebaQualifer() {
		// TODO Auto-generated method stub
		return "Aqui no hay ninguna logica";
	}

	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario retonarPorId(Integer idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Usuario tabla) throws ConflictException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Usuario tabla) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sumar(int... num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
