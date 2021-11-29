package cundi.edu.co.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo de usuario")
public class UsuarioDto extends RepresentationModel<UsuarioDto> {
	
	@NotNull(message = "El id no debe ser vacio")
	@Max(value = 20, message = "El id no es valido")
	@ApiModelProperty(value = "Id del usuario", required = true,  example = "1" )
	private int id;

	@NotBlank(message = "El Nombre es requerido")
	@Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 15 caracteres")
	@ApiModelProperty(value = "Nombre del usuario", required = true,  example = "Natalia" )
	private String nombre;
	
	@NotBlank(message = "Apellido es obligatorio")
	@Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 15 caracteres")
	@ApiModelProperty(value = "Apellido del usuario", required = true,  example = "Velasquez" )
	private String apellido;
	
	@Email(message = "El correo no es valido")
	@Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El correo no es valido patron")
	@ApiModelProperty(value = "Correo del usuario", required = true,  example = "ejemplo@gmail.com" )
	private String correo;
	
	@Min(value = 8000000, message = "El numero de telefono no es valido")
    @Max(value = 8999999, message = "El numero de telefono es mayor al valido")
	@ApiModelProperty(value = "Telefono del usuario", required = true,  example = "8294590" )
	private long telefono;
	
	@NotNull(message = "Los terminos y condiciones son requeridos")
	@AssertTrue(message = "Debe aceptar los terminos y condiciones")
	@ApiModelProperty(value = "Evidencia de aceptacion de terminos y condiciones", required = true,  example = "true" )
	private boolean terminoCondiciones;
	
	//@DateTimeFormat(pattern="yyyy-mm-dd")
	@Past(message = "La Fecha de nacimiento no es valida")
	@ApiModelProperty(value = "Fecha de nacimiento del usuario", required = true,  example = "1999-11-01" )
	private LocalDate fechaNacimiento;
	
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "la contrasena debe tener mínimo ocho caracteres, al menos una letra y un número")
	@ApiModelProperty(value = "Contraseña de para inicio de sesion", required = true,  example = "ejemplo1" )
	private String contrasena;

	public UsuarioDto() {
		
	}
	
	public UsuarioDto(
			@NotNull(message = "El id no debe ser vacio") @Max(value = 20, message = "El id no es valido") int id,
			@NotBlank(message = "El Nombre es requerido") @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 15 caracteres") String nombre,
			@NotBlank(message = "Apellido es obligatorio") @Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 15 caracteres") String apellido,
			@Email(message = "El correo no es valido") @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El correo no es valido patron") String correo,
			@Min(value = 8000000, message = "El numero de telefono no es valido") @Max(value = 8999999, message = "El numero de telefono es mayor al valido") long telefono,
			@NotNull(message = "Los terminos y condiciones son requeridos") @AssertTrue(message = "Debe aceptar los terminos y condiciones") boolean terminoCondiciones,
			@Past(message = "La Fecha de nacimiento no es valida") LocalDate fechaNacimiento,
			@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "la contrasena debe tener mínimo ocho caracteres, al menos una letra, un número y un carácter especial") String contrasena) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.terminoCondiciones = terminoCondiciones;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public boolean isTerminoCondiciones() {
		return terminoCondiciones;
	}

	public void setTerminoCondiciones(boolean terminoCondiciones) {
		this.terminoCondiciones = terminoCondiciones;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
