package cundi.edu.co.demo.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IEstudianteRepo;
import cundi.edu.co.demo.service.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepo repo;

	@Override
	public EstudianteDto retornar(int i) throws ModelNotFoundException, Exception {
		// TODO Auto-generated method stub
		if (i <= 10) {
			EstudianteDto est = new EstudianteDto("Luz", "Velasquez " + i);
			// throw new Exception("Ejemplo");
			return est;
		} else {
			try {
				int x = 0;
				throw new ModelNotFoundException("Estudiante no encontrado");
			} catch (ArithmeticException e) {
				throw new ArithmeticException("No se puede dividir por cero");
			} catch (NullPointerException e) {
				throw new NullPointerException("Objeo nulo no asignado");
			} catch (ModelNotFoundException e) {
				throw new ModelNotFoundException(e.getMessage());
			} catch (Exception e) {
				throw new Exception("Error general");
			}
		}
	}

	@Override
	public List<Estudiante> retornarTodo() {
		return repo.findAll();
	}

	@Override
	public Page<Estudiante> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Estudiante> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Estudiante retonarPorId(Integer idEstudiante) throws ModelNotFoundException {
		// return estudiante.isPresent() ? estudiante.get() : new Estudiante();
//		Optional<Estudiante> estudiante = repo.findById(idEstudiante);
//		if(estudiante.isPresent()) {
//			return estudiante.get();
//		} else {
//			throw new ModelNotFoundException("Estudiante no encontrado");
//		}
		return repo.findById(idEstudiante).orElseThrow(() -> new ModelNotFoundException("Estudiante no encontrado"));
	}

	@Override
	public void guardar(Estudiante estudiante) throws ConflictException {
		// Estudiante estu = this.repo.save(estudiante);
		// return estu;

		/*
		 * Estudiante estudianteBusqueda = repo.findByCedula(estudiante.getCedula()); if
		 * (estudianteBusqueda != null) throw new ConflictException("Cedula ya existe");
		 * 
		 * estudianteBusqueda = repo.findByCorreo(estudiante.getCorreo()); if
		 * (estudianteBusqueda != null) throw new ConflictException("Correo ya existe");
		 */
		
		if (repo.existsByCedula(estudiante.getCedula())) {
			throw new ConflictException("Cedula ya existe");
		}
		if (repo.existsByCorreo(estudiante.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}
		this.repo.save(estudiante);
	}

	@Override
	public void editar(Estudiante estudiante)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		int resultadoCorreo = repo.correoExistente(estudiante.getCorreo(), estudiante.getId());
		
		if(estudiante.getId() != null) {
			if(validarExistenciaPorId(estudiante.getId())) {
				
				Estudiante estudianteAux = this.repo.findById(estudiante.getId()).get();
				estudiante.setCedula(estudianteAux.getCedula());
				
				if (resultadoCorreo == 0) {
					this.repo.save(estudiante);
				}else {
					 throw new ConflictException("Correo ya existe");
				}
							
//				if(estudiante.getCorreo().equals(estudianteAux.getCorreo()))
//					this.repo.save(estudiante);
//				else {
//					if(!repo.existsByCorreo(estudiante.getCorreo())) {
//						this.repo.save(estudiante);
//					} else {
//						 throw new ConflictException("Correo ya existe");
//					}
//				}
				
			} else
				throw new ModelNotFoundException("Estudiante no encontrado");		
		} else {
			throw new ArgumentRequiredException("IdEstudiante es requerido");
		}
		
	}

	@Override
	public void eliminar(int idEstudiante) throws ModelNotFoundException {
		
		if(validarExistenciaPorId(idEstudiante))
			this.repo.deleteById(idEstudiante);
		else
			throw new ModelNotFoundException("Estudiante no encontrado");
	}
	
	private Boolean validarExistenciaPorId(int idEstudiante) {
		return repo.existsById(idEstudiante);
	}

	@Override
	public List<Estudiante> retonarCurso(String curso) throws ModelNotFoundException {
		return repo.listarPorCurso(curso);
	}

	@Override
	public BigInteger cantidad() {
		return (BigInteger) repo.cantidadEstudiantes();
	}

	@Override
	public List<Estudiante> retonarEstudianteEspecifico(String nombre, String curso) throws ModelNotFoundException {
		return repo.findByNombreAndCurso(nombre,curso);
	}

	

}
