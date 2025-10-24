package grandcru.mooca.config;

import grandcru.mooca.domain.Usuario;
import grandcru.mooca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario gabriel = new Usuario();
            gabriel.setNome("Gabriel Maciel");
            gabriel.setEmail("gabriel@grandcru.com");
            gabriel.setSenha(passwordEncoder.encode("123456"));
            usuarioRepository.save(gabriel);
            System.out.println(">>> Usuário 'Gabriel' criado para teste.");
        }
    }
}