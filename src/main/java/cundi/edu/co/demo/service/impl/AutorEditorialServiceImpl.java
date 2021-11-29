package cundi.edu.co.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorEditorialRepo;
import cundi.edu.co.demo.service.IAutorEditorialService;

@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService {

	@Autowired
	private IAutorEditorialRepo repo;

	@Override
	public Page<AutorEditorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AutorEditorial> retornarPaginado(Pageable page) {
		Page<AutorEditorial> pageAutor = repo.findAll(page);
//		pageAutor.getContent().forEach(aut ->{
//	     aut.setLibro(null);
//});

		return pageAutor;
	}

	@Override
	public AutorEditorial retonarPorId(Integer idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(AutorEditorial obj) throws ConflictException {
		// TODO Auto-generated method stub

		// No se puede realizar por que no tenemos la doble referencia
		// this.repo.save(aux);

		// Se podria hacer el find de autor y de editorial y asociar pero traeria mucha
		// informacion solo para usar dos ID's

		// Agregar validaciones respectivas
		this.repo.guardarNativo(obj.getAutor().getId(), obj.getEditorial().getId(), obj.getFecha());
	}

	@Override
	public void editar(AutorEditorial tabla)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(int idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarValidado(AutorEditorial obj) throws ConflictException, ModelNotFoundException {
		// TODO Auto-generated method stub
		if (this.validarExistencia(obj)) {
			this.repo.guardarNativo(obj.getAutor().getId(), obj.getEditorial().getId(), obj.getFecha());
		}
	}

	public Boolean validarExistencia(AutorEditorial obj) throws ConflictException, ModelNotFoundException {
		if (this.repo.llaveExistente(obj.getAutor().getId(), obj.getEditorial().getId()) == 0) {
			if (this.repo.autorExistente(obj.getAutor().getId()) == 1) {
				if (this.repo.editorialExistente(obj.getEditorial().getId()) == 1) {
					return true;
				} else {
					throw new ModelNotFoundException("El id de la editorial no existe");
				}

			} else {
				throw new ModelNotFoundException("El id del autor no existe");
			}

		} else {
			throw new ConflictException("Ya existe la llave");
		}
	}

	@Override
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		if (this.repo.llaveExistente(idAutor, idEditorial) == 1) {
			if (this.repo.autorExistente(idAutor) == 1) {
				if (this.repo.editorialExistente(idEditorial) == 1) {
					this.repo.eliminarNativa(idAutor, idEditorial);
				} else {
					throw new ModelNotFoundException("El id de la editorial no existe");
				}

			} else {
				throw new ModelNotFoundException("El id del autor no existe");
			}

		} else {
			throw new ModelNotFoundException("No existe la relacion");
		}

	}

	@Override
	public Page<AutorEditorial> autoresSegunEditorial(Pageable page, int idEditorial) {
		Page<AutorEditorial> pageAutor = repo.autorPorEditorial(page, idEditorial);
		pageAutor.getContent().forEach(aut ->{
			     aut.getAutor().setLibro(null);
		});

		return pageAutor;
	}

	@Override
	public Page<AutorEditorial> editorialSegunAutor(Pageable page, int idAutor) {
		Page<AutorEditorial> pageAutor = repo.editorialPorAutor(page, idAutor);
		pageAutor.getContent().forEach(aut ->{
			     aut.getAutor().setLibro(null);
		});

		return pageAutor;
	}
	

}
