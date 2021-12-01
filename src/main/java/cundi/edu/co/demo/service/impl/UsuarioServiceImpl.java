package cundi.edu.co.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cundi.edu.co.demo.dto.UsuarioDto;
import cundi.edu.co.demo.entity.Rol;
import cundi.edu.co.demo.entity.Usuario;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IUsuarioRepo;
import cundi.edu.co.demo.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService,  UserDetailsService{
	
	private List<UsuarioDto> usuarios;
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findOneByNick(username);		
		if(usuario == null)
			new UsernameNotFoundException("----Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}

	
	public UsuarioServiceImpl() {
		this.usuarios = new ArrayList<>();
		LocalDate fecha = LocalDate.of(1999, 11, 20);
		usuarios.add(new UsuarioDto(5,"luz","Velasquez","luz@hotmail.com",8297590,true,fecha,"12p45678"));
		usuarios.add(new UsuarioDto(6,"Ana","Bogota","ana@hotmail.com",8297590,true,fecha,"12p45678"));
		usuarios.add(new UsuarioDto(7,"Manuel","vivas","Manuel@hotmail.com",8297590,true,fecha,"12p45678"));
	}

	@Override
	public List<UsuarioDto> retornar() {
		//UsuarioDto usuario = new UsuarioDto(1, "Luz", "Velasquez ", "luz@gmail.com", "luz123", 321201);
		return usuarios;
	}

	@Override
	public UsuarioDto obtener(int i) {
		UsuarioDto usuario = new UsuarioDto();
		for(UsuarioDto u: this.usuarios) {
			if(u.getId() == i) {
				usuario = u;
				break;
			}
		}
		return usuario;
	}

	@Override
	public String pruebaQualifer() {
		// TODO Auto-generated method stub
		return "Aqui se relizaron las validaciones";
	}

	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Usuario retonarPorId(Integer idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Usuario usuario , int rol) throws ConflictException {
		// TODO Auto-generated method stub
		if (repo.existsByDocumento(usuario.getDocumento())) {
			throw new ConflictException("El documento ya existe");
		}
		if (repo.existsByNick(usuario.getNick())) {
			throw new ConflictException("El nick ya existe");
		}
		
		Rol rolGuardar = new Rol();
		rolGuardar.setIdRol(rol);
		
		usuario.setClave(bcrypt.encode(usuario.getClave()).toString());
		usuario.setRol(rolGuardar);
		this.repo.save(usuario);

	}

	@Override
	public void editar(Usuario tabla) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int idTabla) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sumar(int... num) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void guardar(Usuario tabla) throws ConflictException {
		// TODO Auto-generated method stub
		
	}

	
}
