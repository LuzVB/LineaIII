package cundi.edu.co.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
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

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ExceptionWrapper;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IEstudianteService;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/estudiantes")
@Api(value = "CRUD para estudiante")
public class EstudianteController {

	@Autowired
	private IEstudianteService service;

	/*
	 * @GetMapping(value = "/obtener/{i}", produces = "application/json") public
	 * EstudianteDto retornar(@PathVariable int i){ EstudianteDto est = new
	 * EstudianteDto("Luz", "Velasquez "+ i); return est; }
	 */

	@ApiOperation(value = "Obtener estudiante por id", notes = "Se obtiene un estudiante por medio de un id valido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK.", response = EstudianteDto.class),
			@ApiResponse(code = 400, message = "Bad Request. El id no es valido", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping(value = "/obtener/{i}", produces = "application/json")
	public ResponseEntity<EstudianteDto> retornar(
			@ApiParam(value = "Id de un estudiante", required = true) @PathVariable int i)
			throws ModelNotFoundException, Exception {
		EstudianteDto estudiante = service.retornar(i);
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstudianteController.class).retornar(i)).withRel("Obtener estudiante");
		Link link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstudianteController.class).eliminar(i)).withRel("Eliminar estudiante");
		estudiante.add(link).add(link2);
		return new ResponseEntity<EstudianteDto>(estudiante, HttpStatus.OK);
	}

	/*
	 * @PostMapping(value = "/insertar", consumes = "application/json") public void
	 * guardar(@RequestBody EstudianteDto estudiante){ EstudianteDto est = new
	 * EstudianteDto("Luz", "Velasquez"); }
	 */

	@GetMapping(value = "/obtener" ,produces = "application/json")
	public ResponseEntity<?> retonar() {
		List<Estudiante> listaEstudiante = service.retornarTodo();
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPorId/{idEstudiante}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idEstudiante) throws ModelNotFoundException {
		Estudiante estudainte = service.retonarPorId(idEstudiante);
		return new ResponseEntity<Estudiante>(estudainte, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerCurso/{curso}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorCurso(@PathVariable String curso) throws ModelNotFoundException {
		List<Estudiante> listaEstudiantesCurso = service.retonarCurso(curso);
		return new ResponseEntity<List<Estudiante>>(listaEstudiantesCurso, HttpStatus.OK);		
	}	
	
	@GetMapping(value = "/cantidad" ,produces = "application/json")
	public ResponseEntity<?> cantidad() {
		return new ResponseEntity<>(service.cantidad(), HttpStatus.OK);		
	}	
	
	@GetMapping(value = "/obtenerEstudiantesEspecificos/{nombre}/{curso}" ,produces = "application/json")
	public ResponseEntity<?> estudiantesEspecificos(@PathVariable String nombre, @PathVariable String curso) throws ModelNotFoundException {
		List<Estudiante> listaEstudiante = service.retonarEstudianteEspecifico(nombre,curso);
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@ApiOperation(value = "Inserta un Estudiante", notes = "Se crea un estudiante, se debe enviar la informacion correspondiente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Creado", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request. Un dato no es correcto", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Estudiante estudiante) throws ConflictException {
		service.guardar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Actualiza los dato de un estudiante")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se actualizo correctamente", response = ExceptionWrapper.class),
			@ApiResponse(code = 400, message = "Bad Request. Los datos no son validos", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(
			@ApiParam(value = "Objeto de tipo estudiante", required = true) @Valid @RequestBody Estudiante estudiante)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		this.service.editar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina los datos de un estudiante")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Se elimino el estudiante, no retorna", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request. El id no es valido", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping(value = "/eliminar/{i}") // el delete siempre lleva el codigo 204
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id de un usuario existente", required = true) @PathVariable int i) throws ModelNotFoundException {
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@ApiIgnore
	@DeleteMapping(value = "/eliminarHeader/{i}")
	public ResponseEntity<?> eliminarConHeader(@PathVariable int i) {
		EstudianteDto est = new EstudianteDto("Johans", "Gonzalez " + i);
		HttpHeaders header = new HttpHeaders();
		header.add("info1", "valor 1");
		header.add("info2", "valor 2");
		return new ResponseEntity<Object>(header, HttpStatus.NO_CONTENT);
	}

}
