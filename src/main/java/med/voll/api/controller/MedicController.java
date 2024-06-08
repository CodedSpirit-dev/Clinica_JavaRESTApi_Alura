package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medic.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
     * @param dataMedicRegister This is a request body parameter of type DataMedicRegister.
     */
    @PostMapping
    public void registerMedic(@RequestBody @Valid DataMedicRegister dataMedicRegister) {
        medicRepository.save(new Medic(dataMedicRegister));
    }

    /**
     * This method is used to get a list of 'Medic' with pagination.
     * It returns a Page of DataMedicList objects.
     * @param pagination This is a Pageable object for handling pagination.
     * @return Page<DataMedicList> This returns a page of DataMedicList objects.
     */
    @GetMapping
    public Page<DataMedicList> medicList(@PageableDefault(size = 5)  Pageable pagination) {
        //return medicRepository.findAll(pagination).map(DataMedicList::new);
        return medicRepository.findByActiveTrue(pagination).map(DataMedicList::new);
    }

    /**
     * This method is used to update a 'Medic'.
     * It takes a DataUpdateMedic object as input and updates the corresponding 'Medic' in the database.
     * @param dataUpdateMedic This is a request body parameter of type DataUpdateMedic.
     */
    @PutMapping
    @Transactional // Transaction is committed at this level, if there's an error into transaction it is going to execute a rollback
    public void updateMedic(@RequestBody @Valid DataUpdateMedic dataUpdateMedic){ // The request body is used to take the parameters
        Medic medic = medicRepository.getReferenceById(dataUpdateMedic.id());
        medic.updateData(dataUpdateMedic);
    }

    /**
     * This method is used to logically delete a 'Medic'.
     * It takes an id as input and disables the corresponding 'Medic' in the database.
     * @param id This is a path variable of type Long.
     */
    @DeleteMapping("/{id}") //Create a dynamic variable
    @Transactional
    public void deleteMedic(@PathVariable Long id){
        Medic medic = medicRepository.getReferenceById(id);
        medic.disableMedic();
    }

    /*    @DeleteMapping("/{id}") //Create a dynamic variable
    @Transactional
    public void deleteMedic(@PathVariable Long id){
        Medic medic = medicRepository.getReferenceById(id);
        medicRepository.delete(medic);
    }*/
}