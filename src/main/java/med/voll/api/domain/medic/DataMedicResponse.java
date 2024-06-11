package med.voll.api.domain.medic;

import med.voll.api.domain.direction.DataDirection;

public record DataMedicResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String document,
        DataDirection direction

) {
}
