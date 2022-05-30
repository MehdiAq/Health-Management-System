package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public List<Prescription> getPrescriptionHistoryList() {
        List<Prescription> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }


    public List<Prescription> getPatientPrescriptionHistoryList(Long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByPatient_Id(healthInsuranceNumber).isEmpty()){
            throw new RecordNotFoundException("No such Prescription History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
        }
        return repository.findPrescriptionHistoriesByPatient_Id(healthInsuranceNumber);
    }

    public List<Prescription> getPrescriptionHistoryListByMedication(String name) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByMedicationName(name).isEmpty()){
            throw new RecordNotFoundException("No such Prescription History Exists --- Service Name: " + name);
        }
        return repository.findPrescriptionHistoriesByMedicationName(name);
    }

    public List<Prescription> getPrescriptionHistoryListForDate(Date date) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByDateOfPrescription(date).isEmpty()){
            throw new RecordNotFoundException("No such Prescription History Exists --- Date: " + date);
        }
        return repository.findPrescriptionHistoriesByDateOfPrescription(date);
    }

    public void savePrescriptionHistory(Prescription saveService) {
        repository.save(saveService);
    }
}