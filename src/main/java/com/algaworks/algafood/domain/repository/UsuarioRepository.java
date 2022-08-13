package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>{
	
	List<Usuario> findTodosByNomeContaining(String nome);
	Optional<Usuario> findByNome(String nome);
	Optional<Usuario> findByEmail(String email);
	boolean existsByNome(String nome);

}
