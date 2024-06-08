package med.voll.api.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direction.DataDirection;

public record DataUpdateMedic(
        @NotNull //Como es long se usa este, los string usan notblank
        Long id,
        String name,
        @Pattern(regexp = "\\d{4,6}")
        String document,
        @Valid // Sin esta no se activan las validaciones de sistema
        DataDirection direction
) {
}
