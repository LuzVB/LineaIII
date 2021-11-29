package cundi.edu.co.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.repository.ILibroRepo;
import cundi.edu.co.demo.service.IlibroService;

@Service
public class LibroServiceImpl implements IlibroService {

	@Autowired
	private ILibroRepo repo;
	
	@Autowired
	private IAutorRepo autorRepo;
	
	@Override
	public Page<Libro> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Libro> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Libro retonarPorId(Integer idTabla) throws ModelNotFoundException {
		return repo.findById(idTabla).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
	}

	@Override
	public void guardar(Libro tabla) throws ConflictException {
		this.repo.save(tabla);
	}

	@Override
	public void editar(Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		Libro libroConsulta = repo.findById(libro.getId()).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
		libroConsulta.setDescripcion(libro.getDescripcion());
		libroConsulta.setFecha(libro.getFecha());
		libroConsulta.setNumeroPaginas(libro.getNumeroPaginas());
		libroConsulta.setNombre(libro.getNombre());
		repo.save(libroConsulta);
		
	}

	@Override
	public void eliminar(int idTabla) throws ModelNotFoundException {
		if (validarExistenciaPorId(idTabla))
			this.repo.deleteById(idTabla);
		else
			throw new ModelNotFoundException("Libro no encontrado");

	}

	private Boolean validarExistenciaPorId(int idLibro) {
		return repo.existsById(idLibro);
	}

	@Override
	public void guardarLibro(Libro libro, int idAutor) throws ConflictException, ModelNotFoundException {
		Autor autor = autorRepo.findById(idAutor).orElseThrow(() -> new ModelNotFoundException("El id del autor no se ha encontrado"));
		libro.setAutor(autor);
		repo.save(libro);
	}

}
