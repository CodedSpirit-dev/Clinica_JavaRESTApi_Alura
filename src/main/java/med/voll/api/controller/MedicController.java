package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direction.DataDirection;
import med.voll.api.medic.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * This is the controller class for handling requests related to 'Medic'.
 * It uses Spring's @RestController annotation, so it's assumed that all methods return @ResponseBody by default.
 */
@RestController
@RequestMapping("/medics")
public class MedicController {

    // Autowired MedicRepository to use its methods for database operations.
    @Autowired
    private MedicRepository medicRepository;

    /**
     * This method is used to register a new 'Medic'.
     * It takes a DataMedicRegister object as input and saves it to the database.
     * It returns a ResponseEntity with the created Medic's data and a URI to the created Medic.
     * @param dataMedicRegister This is a request body parameter of type DataMedicRegister.
     * @param uriComponentsBuilder This is used to build the URI for the created Medic.
     * @return ResponseEntity<DataMedicResponse> This returns a ResponseEntity with the created Medic's data and a URI to the created Medic.
     */
    @PostMapping
    public ResponseEntity<DataMedicResponse>  registerMedic(@RequestBody @Valid DataMedicRegister dataMedicRegister,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Medic medic = medicRepository.save(new Medic(dataMedicRegister));
        DataMedicResponse dataMedicResponse = new DataMedicResponse(
                medic.getId(), medic.getName(), medic.getEmail(), medic.getPhoneNumber(), medic.getSpeciality().toString(),
                new DataDirection(medic.getDirection().getStreet(), medic.getDirection().getDistrict(), medic.getDirection().getCity(), medic.getDirection().getNumber(),
                        medic.getDirection().getComplement()));
        URI url = uriComponentsBuilder.path("/medics/{id}").buildAndExpand(medic.getId()).toUri();
        return (ResponseEntity) ResponseEntity.created(url).body(dataMedicResponse);
    }

    /**
     * This method is used to get a list of 'Medic' with pagination.
     * It returns a Page of DataMedicList objects.
     * @param pagination This is a Pageable object for handling pagination.
     * @return Page<DataMedicList> This returns a page of DataMedicList objects.
     */
    @GetMapping
    public ResponseEntity<Page<DataMedicList>>  medicList(@PageableDefault(size = 5)  Pageable pagination) {
        //return medicRepository.findAll(pagination).map(DataMedicList::new);
        return ResponseEntity.ok(medicRepository.findByActiveTrue(pagination).map(DataMedicList::new));
    }

    /**
     * This method is used to update a 'Medic'.
     * It takes a DataUpdateMedic object as input and updates the corresponding 'Medic' in the database.
     * It returns a ResponseEntity with the updated Medic's data.
     * @param dataUpdateMedic This is a request body parameter of type DataUpdateMedic.
     * @return ResponseEntity This returns a ResponseEntity with the updated Medic's data.
     */
    @PutMapping
    @Transactional // Transaction is committed at this level, if there's an error into transaction it is going to execute a rollback
    public ResponseEntity updateMedic(@RequestBody @Valid DataUpdateMedic dataUpdateMedic){ // The request body is used to take the parameters
        Medic medic = medicRepository.getReferenceById(dataUpdateMedic.id());
        medic.updateData(dataUpdateMedic);
        return ResponseEntity.ok(new DataMedicResponse(
                medic.getId(), medic.getName(), medic.getEmail(), medic.getPhoneNumber(), medic.getSpeciality().toString(),
                new DataDirection(medic.getDirection().getStreet(), medic.getDirection().getDistrict(), medic.getDirection().getCity(), medic.getDirection().getNumber(),
                        medic.getDirection().getComplement()
                )
        ));

    }

    /**
     * This method is used to logically delete a 'Medic'.
     * It takes an id as input and disables the corresponding 'Medic' in the database.
     * It returns a ResponseEntity with no content.
     * @param id This is a path variable of type Long.
     * @return ResponseEntity This returns a ResponseEntity with no content.
     */
    @DeleteMapping("/{id}") //Create a dynamic variable
    @Transactional
    public ResponseEntity deleteMedic(@PathVariable Long id){
        Medic medic = medicRepository.getReferenceById(id);
        medic.disableMedic();
        return ResponseEntity.noContent().build(); //Creates a 204 status
    }

    @GetMapping("/{id}") //Create a dynamic variable
    public ResponseEntity<DataMedicResponse> returnMedicData(@PathVariable Long id){
        Medic medic = medicRepository.getReferenceById(id);
        var dataMedic = new DataMedicResponse(
                medic.getId(), medic.getName(), medic.getEmail(), medic.getPhoneNumber(), medic.getSpeciality().toString(),
                new DataDirection(medic.getDirection().getStreet(), medic.getDirection().getDistrict(), medic.getDirection().getCity(), medic.getDirection().getNumber(),
                        medic.getDirection().getComplement()
                ));
        return ResponseEntity.ok(dataMedic);
    }
}