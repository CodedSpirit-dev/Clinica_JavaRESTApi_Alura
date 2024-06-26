package med.voll.api.domain.pacient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.domain.direction.DataDirection;

public record DataPacientRegister(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,


        @NotBlank
        @Size(min = 0, max = 15)
        String phoneNumber,
        @NotBlank
        String document,

        @NotNull
        @Valid
        DataDirection direction
) {
}
