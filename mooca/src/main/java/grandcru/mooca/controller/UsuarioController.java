package grandcru.mooca.controller;

import grandcru.mooca.domain.Usuario;
import grandcru.mooca.dto.DadosCadastroUsuario;
import grandcru.mooca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetamos o codificador de senhas

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        if (repository.findByEmail(dados.email()) != null) {
            // Retorna um erro se o email já estiver em uso
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        var usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setTelefone(dados.telefone());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setRole(dados.role());
        usuario.setAtivo(true); // Novo usuário já começa ativo

        // CRIPTOGRAFIA DA SENHA! Passo mais importante.
        usuario.setSenha(passwordEncoder.encode(dados.senha()));

        repository.save(usuario);

        return ResponseEntity.status(201).build();
    }
}