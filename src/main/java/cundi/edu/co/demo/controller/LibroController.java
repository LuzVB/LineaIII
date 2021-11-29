package cundi.edu.co.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IlibroService;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	private IlibroService service;
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Libro> listaAutor = service.retornarPaginado(page);
		return new ResponseEntity<Page<Libro>>(listaAutor, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Libro> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Libro>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPorId/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idLibro) throws ModelNotFoundException {
		Libro libro = service.retonarPorId(idLibro);
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);	
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Libro Libro) throws ConflictException {
		service.guardar(Libro);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/insertar/{autorid}", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Libro Libro , @PathVariable(value = "autorid") int autorid) throws ConflictException, ModelNotFoundException {
		service.guardarLibro(Libro,autorid);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Libro Libro)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		this.service.editar(Libro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/eliminar/{i}") 
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de un libro existente", required = true) @PathVariable int i) throws ModelNotFoundException {
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
