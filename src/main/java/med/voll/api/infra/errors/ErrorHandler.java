package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is responsible for handling exceptions across the application.
 * It uses Spring's @RestControllerAdvice annotation to provide centralized exception handling across all @RequestMapping methods.
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * This method handles EntityNotFoundExceptions.
     * It returns a ResponseEntity with a 404 status code.
     * @return ResponseEntity This returns a ResponseEntity with a 404 status code.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handError404(){
        return ResponseEntity.notFound().build();
    }

    /**
     * This method handles MethodArgumentNotValidExceptions.
     * It returns a ResponseEntity with a 400 status code and a list of validation errors.
     * @param e This is the exception that was thrown.
     * @return ResponseEntity This returns a ResponseEntity with a 400 status code and a list of validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handError400(MethodArgumentNotValidException e){
        var errors = e.getAllErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * This is a record class that represents a validation error.
     * It includes the field that caused the error and the error message.
     */
    private record DataErrorValidation(String field, String error){
        /**
         * This is the constructor for the DataErrorValidation record.
         * It takes an ObjectError as input and initializes the field and error fields appropriately.
         * @param error This is the ObjectError that was thrown.
         */
        public DataErrorValidation(ObjectError error){
            this(((FieldError) error).getField(), error.getDefaultMessage());
        }
    }
}