package med.voll.api.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direction.DataDirection;

public record DataMedicRegister
        (
                @NotBlank
                String name,
                @NotBlank
                @Email
                String email,
                @NotBlank
                @Pattern(regexp = "\\d{4,6}")
                String document,
                @NotNull // Ya que es un ENUM
                Speciality speciality,
                @NotBlank
                String phoneNumber,
                @NotNull // Ya que es un objeto
                @Valid // Sin esta no se activan las validaciones de sistema
                DataDirection direction
        ) {
}
