package cundi.edu.co.demo.service;

import java.math.BigInteger;
import java.util.List;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IEstudianteService extends ICrud<Estudiante, Integer> {
	
	public EstudianteDto retornar(int i) throws ModelNotFoundException, Exception;
	
	public List<Estudiante> retornarTodo();
		
	public List<Estudiante>  retonarCurso(String curso) throws ModelNotFoundException;
	
	public List<Estudiante>  retonarEstudianteEspecifico(String nombre,String curso) throws ModelNotFoundException;
	
	public BigInteger cantidad();

}
