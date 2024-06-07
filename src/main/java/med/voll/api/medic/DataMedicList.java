package med.voll.api.medic;

public record DataMedicList(
        String name,
        String speciality,
        String document,
        String email
) {
    public DataMedicList(Medic medic) {
        this(medic.getName(), medic.getSpeciality().toString(), medic.getDocument(), medic.getEmail());
    }
}
