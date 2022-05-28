package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class PatientService {

    private final PatientRepository repository;

    private final ObjectMapper mapper= new ObjectMapper();

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getPatientList() {
        List<Patient> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public Patient getPatientById(long id) throws RecordNotFoundException {
        Optional<Patient> patient =  repository.findById(id);
        if(patient.isPresent()){
            return mapper.convertValue(patient.get(), Patient.class);
        }
        else{
            throw new RecordNotFoundException("Patient with ID: " + id +" not found");
        }
    }

    public Patient saveOrUpdatePatient(Patient patient){
        if(patient.getId() == null){
            repository.save(patient);
            return patient;
        }
        else{
            Optional<Patient> patient1 = repository.findById(patient.getId());
            if (patient1.isPresent()) {
                Patient patient2 = patient1.get(); //the data that I fetched from db
                patient2.setId(patient.getId());
                patient2.setFirstName(patient.getFirstName());
                patient2.setLastName(patient.getLastName());
                patient2.setAddress(patient.getAddress());
                patient2.setCardExpiration(patient.getCardExpiration());
                patient2.setBirthDate(patient.getBirthDate());
                patient2.setEmail(patient.getEmail());
                patient2.setPhoneNumber(patient.getPhoneNumber());
                patient2.setFamilyDoctor(patient.getFamilyDoctor());
                patient2 = repository.save(patient2);

                return patient2;
            }
            else{
                repository.save(patient);
                return patient;
            }
        }
    }

    public void deletePatient(long patientId) {
        repository.deleteById(patientId);
    }
}