package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;

@Service
public class PrescriptionHistoryService {

    private final PrescriptionHistoryRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    public PrescriptionHistoryService(PrescriptionHistoryRepository repository) {
        this.repository = repository;
    }

    public List<PrescriptionHistory> getPrescriptionHistoryList() {
        List<PrescriptionHistory> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }


    public List<PrescriptionHistory> getPatientPrescriptionHistoryList(long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByPatient_HealthInsuranceNumber(healthInsuranceNumber) == null){
            throw new RecordNotFoundException("No such Prescription History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
        }
        return repository.findPrescriptionHistoriesByPatient_HealthInsuranceNumber(healthInsuranceNumber);
    }

    public List<PrescriptionHistory> getPrescriptionHistoryListByMedication(String name) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByMedicationName(name) == null){
            throw new RecordNotFoundException("No such Prescription History Exists --- Service Name: " + name);
        }
        return repository.findPrescriptionHistoriesByMedicationName(name);
    }

    public List<PrescriptionHistory> getPrescriptionHistoryListForDate(Date date) throws RecordNotFoundException {
        if(repository.findPrescriptionHistoriesByDateOfPrescription(date) == null){
            throw new RecordNotFoundException("No such Prescription History Exists --- Date: " + date);
        }
        return repository.findPrescriptionHistoriesByDateOfPrescription(date);
    }

    public void savePrescriptionHistory(PrescriptionHistory saveService) {
        repository.save(saveService);
    }
}