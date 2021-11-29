package cundi.edu.co.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Autor;

@Repository
public interface IAutorRepo  extends JpaRepository<Autor, Integer> {


	@Query(value = "SELECT id, apellido, cedula, correo, nombre FROM autor",
		       countQuery = "SELECT count(*) FROM autor ",
		       nativeQuery = true)
	Page<Autor> findAll(Pageable pageable);  
	
	public Boolean existsByCedula(String cedula);

	public Boolean existsByCorreo(String correo);
	
	// correo existe sin tener en cuenta el propio
	@Query(value = "SELECT COUNT(a) FROM Autor a WHERE a.correo = :correo AND a.id != :id")
	int correoExistente(@Param("correo") String correo, @Param("id") int id);
	
	// correo existe sin tener en cuenta el propio
	@Query(value = "SELECT COUNT(a) FROM Autor a WHERE a.cedula = :cedula AND a.id != :id")
	int cedulaExistente(@Param("cedula") String cedula, @Param("id") int id);
	
	@Query(value = "SELECT a FROM Autor a", nativeQuery = false)
	Page<Autor> retornarPaginadoConsulta(Pageable pageable);
}
