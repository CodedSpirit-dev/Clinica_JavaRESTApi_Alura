package med.voll.api.medic;

import med.voll.api.direction.DataDirection;

public record DataMedicResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String document,
        DataDirection direction

) {
}
