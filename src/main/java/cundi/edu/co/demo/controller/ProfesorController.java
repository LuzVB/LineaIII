package cundi.edu.co.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.ProfesorDto;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/profesores")
@Api(value = "CRUD para profesores")
public class ProfesorController {

	@Autowired
	private IProfesorService service;
	
	@GetMapping(value = "/obtener" ,produces = "application/json")
	public ResponseEntity<?> retonar() throws InterruptedException, ExecutionException {
		return new ResponseEntity<List<ProfesorDto>>(service.retornar(), HttpStatus.OK);	
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo Profesor", required = true) @Valid @RequestBody ProfesorDto profesor) throws InterruptedException, ExecutionException, Exception{
		service.guardar(profesor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody ProfesorDto profesor) throws InterruptedException, ExecutionException, Exception {
		this.service.editar(profesor);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de un usuario existente", required = true) @PathVariable String id) throws InterruptedException, ExecutionException, Exception {
		this.service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
