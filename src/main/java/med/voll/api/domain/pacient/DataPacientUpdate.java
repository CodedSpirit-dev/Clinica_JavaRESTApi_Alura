package med.voll.api.domain.pacient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direction.DataDirection;

public record DataPacientUpdate(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        DataDirection direction
) {
}