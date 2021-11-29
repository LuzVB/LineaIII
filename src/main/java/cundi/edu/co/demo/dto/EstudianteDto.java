package cundi.edu.co.demo.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.*;

@ApiModel("Modelo de Estudiante")
public class EstudianteDto extends RepresentationModel<EstudianteDto> {
	
	@NotBlank
	@ApiModelProperty(value = "Nombre del estudiante", required = true,  example = "Natalia" )
	private String nombre; 
	
	@ApiModelProperty(value = "Apellido del estudiante", required = true,example = "Velasquez"  )
	private String apellido;
	

	public EstudianteDto(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
