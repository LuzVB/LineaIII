package cundi.edu.co.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.demo.entity.Autor;

public interface IAutorService  extends ICrud<Autor, Integer> {

	Page<Autor> retornarPaginadoConsulta(Pageable page);
}
