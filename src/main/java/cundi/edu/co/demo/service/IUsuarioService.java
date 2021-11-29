package cundi.edu.co.demo.service;

import java.util.List;

import cundi.edu.co.demo.dto.UsuarioDto;
import cundi.edu.co.demo.entity.Usuario;

public interface IUsuarioService extends ICrud<Usuario, Integer>  {
	
	public List<UsuarioDto> retornar();
	
	public UsuarioDto obtener(int i); 
	
	public String pruebaQualifer();

	public int sumar(int... num);
}
