package cundi.edu.co.demo.controller;

import java.util.List;

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

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IAutorEditorialService;
import cundi.edu.co.demo.service.IAutorService;
import cundi.edu.co.demo.service.IEditorialService;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {
	
	@Autowired
	private IEditorialService service;
	
	@Autowired
	private IAutorEditorialService serviceAE;
	
	@PostMapping(value = "/asociarEditorial", consumes = "application/json")
	public ResponseEntity<?> asociarEditorail(@Valid @RequestBody AutorEditorial autorEditorial) throws ConflictException, ModelNotFoundException {
		serviceAE.guardarValidado(autorEditorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/eliminarAsociacion/{idAutor}/{idEditorial}") 
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de un usuario existente", required = true) @PathVariable int idAutor, @PathVariable int idEditorial ) throws ModelNotFoundException {
		this.serviceAE.eliminarNativo(idAutor, idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/autoresPorEditorial/{idEditorial}" ,produces = "application/json")
	public ResponseEntity<?> autoresPorEditorial(Pageable page, @PathVariable int idEditorial) {
		Page<AutorEditorial> listaAutor = serviceAE.autoresSegunEditorial(page, idEditorial);
		return new ResponseEntity<Page<AutorEditorial>>(listaAutor, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/editorialesPorAutor/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> editorialesPorAutor(Pageable page, @PathVariable int idAutor) {
		Page<AutorEditorial> listaAutor = serviceAE.editorialSegunAutor(page, idAutor);
		return new ResponseEntity<Page<AutorEditorial>>(listaAutor, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerRelacion" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<AutorEditorial> listaAutor = serviceAE.retornarPaginado(page);
		return new ResponseEntity<Page<AutorEditorial>>(listaAutor, HttpStatus.OK);	
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo Editorial", required = true) @Valid @RequestBody Editorial editorial) throws ConflictException {
		service.guardar(editorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(
			@ApiParam(value = "Objeto de tipo Editorial", required = true) @Valid @RequestBody Editorial editorial)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		this.service.editar(editorial);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{i}") 
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de una editorial existente", required = true) @PathVariable int i) throws ModelNotFoundException {
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/obtenerPorId/{idEditorial}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idEditorial) throws ModelNotFoundException {
		Editorial estudainte = service.retonarPorId(idEditorial);
		return new ResponseEntity<Editorial>(estudainte, HttpStatus.OK);	
	}

}
