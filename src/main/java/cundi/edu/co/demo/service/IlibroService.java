package cundi.edu.co.demo.service;

import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IlibroService extends ICrud<Libro, Integer> {

	public void guardarLibro(Libro libro , int idAutor)  throws ConflictException, ModelNotFoundException;
	
}
