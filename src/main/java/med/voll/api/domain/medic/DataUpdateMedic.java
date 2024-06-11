package med.voll.api.domain.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direction.DataDirection;

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
