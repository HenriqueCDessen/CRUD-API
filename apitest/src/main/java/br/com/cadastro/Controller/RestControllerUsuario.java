package br.com.cadastro.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import br.com.cadastro.Repository.UsuarioRepository;
import br.com.cadastro.usuarios.Usuario;

@RestController
@RequestMapping(value = "usuario-management/api/usuario", produces = {MediaType.APPLICATION_JSON_VALUE})

public class RestControllerUsuario {

  @Autowired
  private UsuarioRepository repository;

  public UsuarioRepository getRepository() {
    return repository;
  }

  public void setRepository(UsuarioRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "find-all")
  public List<Usuario> getAllUsuario() {
    return repository.findAll();
  }

  @PostMapping("create")
  Usuario createOrSaveUsuario(@RequestBody Usuario newUsuario) {
    return repository.save(newUsuario);
  }

  @GetMapping("find-by-id/{id}")
  Usuario getUsuarioById(@PathVariable Long id) {
    return repository.findById(id).get();
  }

  @PutMapping("update/{id}")
  Usuario updateUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {

    return repository.findById(id).map(usuario -> {
      usuario.setNome(newUsuario.getNome());
      usuario.setSobrenome(newUsuario.getSobrenome());
      usuario.setTelefone(newUsuario.getTelefone());
      usuario.setEmail(newUsuario.getEmail());
      usuario.setCpf(newUsuario.getCpf());
      return repository.save(usuario);
    }).orElseGet(() -> {
      newUsuario.setId(id);
      return repository.save(newUsuario);
    });
  }

  @DeleteMapping("delete/{id}")
  void deleteUsuario(@PathVariable Long id) {
    repository.deleteById(id);
  }
}

