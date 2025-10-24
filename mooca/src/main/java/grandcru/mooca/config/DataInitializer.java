package grandcru.mooca.config;

import grandcru.mooca.domain.Usuario;
import grandcru.mooca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            System.out.println(">>> Criando usuários de teste...");


            Usuario gerente = new Usuario();
            gerente.setNome("Gerente Loja");
            gerente.setEmail("gerente@grandcru.com");
            gerente.setSenha(passwordEncoder.encode("admin123"));
            gerente.setRole("ROLE_GERENTE");
            gerente.setAtivo(true);
            gerente.setDataNascimento(LocalDate.of(1990, 1, 1));
            usuarioRepository.save(gerente);

            Usuario gabriel = new Usuario();
            gabriel.setNome("Gabriel Maciel");
            gabriel.setEmail("gabriel@grandcru.com");
            gabriel.setSenha(passwordEncoder.encode("vendedor123"));
            gabriel.setRole("ROLE_VENDEDOR");
            gabriel.setAtivo(true);
            gabriel.setDataNascimento(LocalDate.of(1995, 5, 10));
            usuarioRepository.save(gabriel);

            System.out.println(">>> Usuários de teste criados com sucesso!");
        }
    }
}