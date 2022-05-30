package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import MFD.HealthManagementSystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new RecordNotFoundException("There is no patient");
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
                patient2.setFirst_name(patient.getFirst_name());
                patient2.setLast_name(patient.getLast_name());
                patient2.setAddress(patient.getAddress());
                patient2.setHealth_card_expiration_date(patient.getHealth_card_expiration_date());
                patient2.setYear_of_birth(patient.getYear_of_birth());
                patient2.setEmail(patient.getEmail());
                patient2.setCellphone(patient.getCellphone());
                patient2.setFamily_doctor(patient.getFamily_doctor());
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
