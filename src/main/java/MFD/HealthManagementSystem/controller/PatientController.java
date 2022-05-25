package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patient")
    public Patient createPatient(@Valid @RequestBody Patient patient){
        return patientRepository.save(patient);
    }
}
