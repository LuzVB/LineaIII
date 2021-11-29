package cundi.edu.co.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepo repo;

	@Override
	public Page<Autor> retornarPaginado(int page, int size) {
		Page<Autor> pageAutor = repo.findAll(PageRequest.of(page,size));
		pageAutor.getContent().forEach(aut ->{
			     aut.setLibro(null);
		});
		return pageAutor;
	}

	@Override
	public Page<Autor> retornarPaginado(Pageable page) {
		Page<Autor> pageAutor = repo.findAll(page);
		pageAutor.getContent().forEach(aut ->{
			     aut.setLibro(null);
		});
		
		return pageAutor;
	}
	
	@Override
	public Page<Autor> retornarPaginadoConsulta(Pageable page) {
		Page<Autor> pageAutor = repo.retornarPaginadoConsulta(page);
		pageAutor.getContent().forEach(aut ->{
			     aut.setLibro(null);
		});
		return pageAutor;
	}

	@Override
	public Autor retonarPorId(Integer idAutor) throws ModelNotFoundException {
		Optional<Autor> autor = repo.findById(idAutor);
		if(autor.isPresent()) {
			return autor.get();
			/*Autor autorAux = autor.get(); Si no quiero los libros
			autorAux.setLibro(null);
			return autorAux;*/
		} else {
			throw new ModelNotFoundException("Autor no encontrado");
		}
	}

	@Override
	public void guardar(Autor autor) throws ConflictException {

		if (repo.existsByCedula(autor.getCedula())) {
			throw new ConflictException("Cedula ya existe");
		}
		if (repo.existsByCorreo(autor.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}
		
		if(autor.getLibro() != null) {
			autor.getLibro().forEach(libro ->{
				libro.setAutor(autor);
			});
		}
		this.repo.save(autor);
		
	}

	@Override
	public void editar(Autor autor) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		int resultadoCorreo = repo.correoExistente(autor.getCorreo(), autor.getId());
		int resultadoCedula = repo.cedulaExistente(autor.getCedula(), autor.getId());		
		if(autor.getId() != null) {
			Autor autorConsulta = repo.findById(autor.getId()).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
			if (resultadoCorreo == 0) {
				if(resultadoCedula == 0) {
					autorConsulta.setNombre(autor.getNombre());
					autorConsulta.setApellido(autor.getApellido());
					autorConsulta.setCedula(autor.getCedula());
					autorConsulta.setCorreo(autor.getCorreo());
					this.repo.save(autor);
				}else {
					 throw new ConflictException("Cedula ya existe");
				}
			}else {
				 throw new ConflictException("Correo ya existe");
			}
		} else {
			throw new ArgumentRequiredException("El id del autor es requerido");
		}
	}

	@Override
	public void eliminar(int idAutor) throws ModelNotFoundException {
		if(validarExistenciaPorId(idAutor))
			this.repo.deleteById(idAutor);
		else
			throw new ModelNotFoundException("Estudiante no encontrado");
	}
	
	private Boolean validarExistenciaPorId(int idEstudiante) {
		return repo.existsById(idEstudiante);
	}

}
