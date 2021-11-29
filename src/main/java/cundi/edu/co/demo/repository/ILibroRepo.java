package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {

}
