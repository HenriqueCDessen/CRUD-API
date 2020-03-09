package br.com.cadastro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cadastro.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
