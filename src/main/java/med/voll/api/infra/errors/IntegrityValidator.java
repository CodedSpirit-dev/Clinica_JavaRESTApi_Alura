package med.voll.api.infra.errors;

public class IntegrityValidator extends RuntimeException {
    public IntegrityValidator(String s){
        super(s);
    }
}
