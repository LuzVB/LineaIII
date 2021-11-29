package cundi.edu.co.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IAutorEditorialService extends ICrud<AutorEditorial, Integer> {

	public void guardarValidado(AutorEditorial obj) throws ConflictException, ModelNotFoundException;
	
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;
	
	public Page<AutorEditorial> autoresSegunEditorial(Pageable page, int idEditorial);
	
	public Page<AutorEditorial> editorialSegunAutor(Pageable page, int idAutor);
}
