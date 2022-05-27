package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
public class PatientRestController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patients")
    public Patient createPatient(@Valid @RequestBody Patient patient){
        return patientRepository.save(patient);
    }

}
