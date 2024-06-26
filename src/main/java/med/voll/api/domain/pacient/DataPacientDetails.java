package med.voll.api.domain.pacient;

public record DataPacientDetails(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String document,
        med.voll.api.domain.direction.Direction direction
) {
    public DataPacientDetails(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getPhoneNumber(), pacient.getDocument(), pacient.getDirection());
    }
}
