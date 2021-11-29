package cundi.edu.co.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.dto.UsuarioDto;
import cundi.edu.co.demo.exception.ExceptionWrapper;
import cundi.edu.co.demo.service.IUsuarioService;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/usuarios")
@Api(value = "/usuarios")
@Validated
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private IUsuarioService service;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer = "List", response = UsuarioDto.class),
			@ApiResponse(code = 400, message = "Bad Request. No se encontro la solicitud", response = ExceptionWrapper.class),
			@ApiResponse(code = 404, message = "No se encontro la lista solicitada", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionWrapper.class) })
	@GetMapping(value = "/obtener", produces = "application/json")
	@ApiOperation(httpMethod = "GET", value = "Retorna una lista de todos los usuarios")
	public ResponseEntity<List<UsuarioDto>> retornar() {
		List<UsuarioDto> usuario = service.retornar();
		for (UsuarioDto us : usuario) {
			Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).obtenerId(us.getId())).withRel("Obtener datos usuario especifico");
			Link link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).obtenerNombre(us.getNombre())).withRel("Nombre del usuario");
			Link link3 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).eliminar(us.getId())).withRel("Eliminar usuario");
			us.add(link).add(link2).add(link3);
		}
		return new ResponseEntity<List<UsuarioDto>>(usuario, HttpStatus.OK);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UsuarioDto.class),
			@ApiResponse(code = 400, message = "Bad Request. No se encontro la solicitud", response = ExceptionWrapper.class),
			@ApiResponse(code = 404, message = "No se encontro el usuario solicitado", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionWrapper.class) })
	@ApiOperation("Retorna los datos de un usuario por su Id")
	@GetMapping(value = "/obtenerId/{i}", produces = "application/json")
	public ResponseEntity<UsuarioDto> obtenerId(
			@ApiParam(value = "Id de un usuario existente el numero minimo es 5", required = true) @PathVariable @Min(5) int i) {
		UsuarioDto usuario = service.obtener(i);
		return new ResponseEntity<UsuarioDto>(usuario, HttpStatus.OK);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request. No se encontro la solicitud", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@ApiOperation("Retorna el mismo nombre que se envia por la url")
	@GetMapping(value = "/obtenerNombre/{nombre}", produces = "application/json")
	public ResponseEntity<?> obtenerNombre(
			@ApiParam(value = "Agregar un nombre debe tener mino 3 caracteres maximo 30", required = true) @PathVariable @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres") String nombre) {
		String usuario = nombre;
		return new ResponseEntity<Object>(usuario, HttpStatus.OK);
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Creado correctamente", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request. Un dato no se esta enviando correctamente", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionWrapper.class) })
	@ApiOperation("Inserta un usuario")
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(
			@ApiParam("Informacion de usuario para ser creado") @Valid @RequestBody UsuarioDto usuario) {
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).eliminar(usuario.getId())).withRel("Eliminar usuario");
		return new ResponseEntity<Object>(link,HttpStatus.CREATED);
	}

	@ApiIgnore
	@GetMapping(value = "/prueba", produces = "application/json")
	public ResponseEntity<?> prueba() {
		String prueba = service.pruebaQualifer();
		return new ResponseEntity<Object>(prueba, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina el registro de un usuario")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Se elimino el usuario, no retorna", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request. El id no es valido", response = ExceptionWrapper.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionWrapper.class) })
	@DeleteMapping(value = "/eliminar/{i}") // el delete siempre lleva el codigo 204
	public ResponseEntity<?> eliminar(
			@ApiParam(value = "Id del usuario existente", required = true) @PathVariable int i) {
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
