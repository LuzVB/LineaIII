package cundi.edu.co.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Cedula es obligatorio")
	@Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres")
	@Column(name = "cedula", length = 12, nullable = false, unique = true)
	private String cedula;

	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres")
	@Column(name = "nombre", length = 15, nullable = false)
	private String nombre;

	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres")
	@Column(name = "apellido", length = 15, nullable = false)
	private String apellido;

	@NotNull(message = "correo es obligatorio")
	@Email(message = "Email incorrecto")
	@Column(name = "correo", length = 60, nullable = false, unique = true)
	private String correo;
	
	@NotNull(message = "curso es obligatorio")
	@Size(min = 3, max = 3, message = "El curso debe tener 3 caracterez")
	@Column(name = "curso", length = 3, nullable = false)
	private String curso;


	public Estudiante() {

	}
	
	
	public Estudiante(Integer id, String cedula, String nombre, String apellido, String correo, String curso) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}

	
}
