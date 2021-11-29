package cundi.edu.co.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IEditorialRepo;
import cundi.edu.co.demo.service.IEditorialService;

@Service
public class EditorialServiceImpl  implements IEditorialService {

	@Autowired
	private IEditorialRepo repo;
	
	@Override
	public Page<Editorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Editorial> retornarPaginado(Pageable page) {
		Page<Editorial> pageAutor = repo.findAll(page);
//		pageAutor.getContent().forEach(aut ->{
//			     aut.setLibro(null);
//		});
		
		return pageAutor;
	}

	@Override
	public Editorial retonarPorId(Integer idEditorial) throws ModelNotFoundException {
		Optional<Editorial> editorial = repo.findById(idEditorial);
		if(editorial.isPresent()) {
			return editorial.get();
			/*Autor autorAux = autor.get(); Si no quiero los libros
			autorAux.setLibro(null);
			return autorAux;*/
		} else {
			throw new ModelNotFoundException("Editorial no encontrada");
		}
	}

	@Override
	public void guardar(Editorial editorial) throws ConflictException {
		if (repo.existsByNit(editorial.getNit())) {
			throw new ConflictException("El Nit ya se encuentra registrado");
		}
		
		this.repo.save(editorial);
	}

	@Override
	public void editar(Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		if(editorial.getId() != null) {
			Editorial editorialConsulta = repo.findById(editorial.getId()).orElseThrow(() -> new ModelNotFoundException("Editorial no encontrada"));
			int resultadoCorreo = repo.nitExistente(editorial.getNit(), editorial.getId());		
			if (resultadoCorreo == 0) {
				editorialConsulta.setNit(editorial.getNit());
				editorialConsulta.setNombre(editorial.getNombre());
				editorialConsulta.setCorreo(editorial.getCorreo());
				this.repo.save(editorial);
			}else {
				 throw new ConflictException("El nit ya existe");
			}
		} else {
			throw new ArgumentRequiredException("El id de la editorial es requerido");
		}
		
	}

	@Override
	public void eliminar(int idEditorial) throws ModelNotFoundException {
		if(repo.existsById(idEditorial))
			this.repo.deleteById(idEditorial);
		else
			throw new ModelNotFoundException("Editorial no encontrada");
		
	}

}
