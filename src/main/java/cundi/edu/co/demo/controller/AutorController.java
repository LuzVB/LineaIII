package cundi.edu.co.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IAutorService;
import io.swagger.annotations.ApiParam;

//@PreAuthorize("hasAuthority('Administrador')")
//@PreAuthorize("hasAuthority('Vendedor')")
@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private IAutorService service;
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Autor> listaAutor = service.retornarPaginado(page);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Autor> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Autor>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPorId/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idAutor) throws ModelNotFoundException {
		Autor estudainte = service.retonarPorId(idAutor);
		return new ResponseEntity<Autor>(estudainte, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginadoConsulta" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginadoConsulta(Pageable page) {
		Page<Autor> listaAutor = service.retornarPaginadoConsulta(page);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
	}	
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Autor autor) throws ConflictException {
		service.guardar(autor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Autor autor)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		this.service.editar(autor);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/eliminar/{i}") 
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de un usuario existente", required = true) @PathVariable int i) throws ModelNotFoundException {
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
