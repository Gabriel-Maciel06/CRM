package grandcru.mooca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        String telefone,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        String role // "ROLE_VENDEDOR" ou "ROLE_GERENTE"
) {
}