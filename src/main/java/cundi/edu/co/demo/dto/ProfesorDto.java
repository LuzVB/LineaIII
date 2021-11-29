package cundi.edu.co.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfesorDto {

	private String id;

	@NotNull(message = "Cedula es obligatorio")
	@Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres")
	private String cedula;

	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres")
	private String nombre;

	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres")
	private String apellido;

	@NotNull(message = "correo es obligatorio")
	@Email(message = "Email incorrecto")
	private String correo;

	public ProfesorDto() {
	}
	
	public ProfesorDto(String id,String cedula, String nombre, String apellido,String correo) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
