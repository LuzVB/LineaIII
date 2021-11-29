package cundi.edu.co.demo.repository;


import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.entity.Editorial;

@Repository
public interface IAutorEditorialRepo extends JpaRepository<AutorEditorial, Integer> {

	// Para INSERT UPDATE Y DELETE DEBEN COLOCAR EL @Transactional Y EL @Modifying
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_editorial(id_autor, id_editorial, fecha) VALUES(:idAutor, :idEditorial, :fecha)", nativeQuery = true)
	void guardarNativo(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial,
			@Param("fecha") LocalDate fecha);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM autor_editorial where id_autor = :idAutor and id_editorial = :idEditorial ", nativeQuery = true)
	void eliminarNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
	
	@Query(value = "select count(p) from autor_editorial p where p.id_autor = :idAutor and p.id_editorial = :idEditorial", nativeQuery = true)
	int llaveExistente(@Param("idAutor") int idAutor, @Param("idEditorial") int idEditorial);
	
	@Query(value = "select count(p) from autor p where p.id = :idAutor ", nativeQuery = true)
	int autorExistente(@Param("idAutor") int idAutor);
	
	@Query(value = "select count(p) from editorial p where p.id = :idEditorial ", nativeQuery = true)
	int editorialExistente(@Param("idEditorial") int idEditorial);
	
	@Query(value = "SELECT * FROM autor_editorial ae JOIN autor a ON a.id = ae.id_autor WHERE ae.id_editorial = :idEditorial", nativeQuery = true)
	Page<AutorEditorial> autorPorEditorial(Pageable pageable, @Param("idEditorial") int idEditorial);
	
	@Query(value = "SELECT * FROM autor_editorial ae JOIN editorial a ON a.id = ae.id_editorial WHERE ae.id_autor = :idAutor", nativeQuery = true)
	Page<AutorEditorial> editorialPorAutor(Pageable pageable, @Param("idAutor") int idAutor);
	
}
