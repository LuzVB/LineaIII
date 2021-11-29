package cundi.edu.co.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Estudiante;

@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer> {

	public Estudiante findByCedula(String cedula);

	public Estudiante findByCorreo(String correo);

	public Boolean existsByCedula(String cedula);

	public Boolean existsByCorreo(String correo);

	// JPQL
	@Query(value = "SELECT c FROM Estudiante c WHERE c.curso = :curso")
	List<Estudiante> listarPorCurso(@Param("curso") String curso);

	// SQL
	@Query(value = "select count(*) from estudiante ", nativeQuery  = true)
	Object cantidadEstudiantes();

	// correo existe sin tener en cuenta el propio
	@Query(value = "SELECT COUNT(c) FROM Estudiante c WHERE c.correo = :correo AND c.id != :id")
	int correoExistente(@Param("correo") String correo, @Param("id") int id);

 
	// findBy todas las opciones
	List<Estudiante> findByNombreAndCurso(String nombre, String curso);
}
