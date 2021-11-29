package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Editorial;

@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {

	public Boolean existsByNit(String nit);
	
	@Query(value = "SELECT COUNT(a) FROM Editorial a WHERE a.nit = :nit AND a.id != :id")
	int nitExistente(@Param("nit") String nit, @Param("id") int id);
}