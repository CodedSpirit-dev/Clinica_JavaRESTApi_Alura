package med.voll.api.medic;

import med.voll.api.direction.DataDirection;

public record DataMedicRegister(String name, String email, String document, Speciality speciality, DataDirection direction) {
}
