package med.voll.api.domain.pacient;

public record DataPacientList(
    Long id,
    String name,
    String email,
    String document
) {

    public DataPacientList(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getDocument());
    }
}
