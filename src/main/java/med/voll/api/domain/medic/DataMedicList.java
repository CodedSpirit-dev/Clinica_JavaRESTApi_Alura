package med.voll.api.domain.medic;

public record DataMedicList(
        Long id,
        String name,
        String speciality,
        String document,
        String email
) {
    public DataMedicList(Medic medic) {
        this(medic.getId() ,medic.getName(), medic.getSpeciality().toString(), medic.getDocument(), medic.getEmail());
    }
}
