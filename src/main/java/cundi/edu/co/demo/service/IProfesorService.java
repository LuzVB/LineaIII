package cundi.edu.co.demo.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cundi.edu.co.demo.dto.ProfesorDto;

public interface IProfesorService {

	public List<ProfesorDto> retornar() throws InterruptedException, ExecutionException;

	public void guardar(ProfesorDto profesor) throws InterruptedException, ExecutionException, Exception;

	public void editar(ProfesorDto profesor)throws InterruptedException, ExecutionException, Exception;

	public void eliminar(String idProfesor)throws InterruptedException, ExecutionException, Exception;
}
