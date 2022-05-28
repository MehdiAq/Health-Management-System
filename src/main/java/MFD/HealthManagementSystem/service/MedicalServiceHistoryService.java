package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;

@Service
public class MedicalServiceHistoryService {

    private final MedicalServiceHistoryRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();


    public MedicalServiceHistoryService(MedicalServiceHistoryRepository repository) {
        this.repository = repository;
    }

    public List<MedicalServiceHistory> getMedicalServiceHistoryServiceList() {
        List<MedicalServiceHistory> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public MedicalServiceHistory saveOrUpdateMedServiceHistory(MedicalServiceHistory history){
        if(repository.findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(history.getPatient().getHealthInsuranceNumber(), history.getServiceName(), history.getServiceDate()) == null){
            repository.save(history);
            return history;
        }
        else{
            MedicalServiceHistory history1 = repository.findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(history.getPatient().getHealthInsuranceNumber(), history.getServiceName(), history.getServiceDate());
            if (history1 != null) {
                MedicalServiceHistory history2 = history1;
                history2.setPatient(history.getPatient());
                history2.setServiceName(history.getServiceName());
                history2.setServiceDate(history.getServiceDate());
                history2.setDoctor(history.getDoctor());
                history2 = repository.save(history2);

                return history2;
            }
            else{
                repository.save(history);
                return history;
            }
        }
    }

    public void deleteServiceHistoryByPatientAndServiceAndDate(long healthInsuranceNumber, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber + " --- Service: " + serviceName + " --- Date: " + date);
        }
        repository.deleteMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date);
    }


    public MedicalServiceHistory getMedicalServiceHistory(Long healthInsuranceNumber, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber + " --- Service: " + serviceName + " --- Date: " + date);
        }
        return repository.findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date);
    }

    public List<MedicalServiceHistory> getPatientMedicalServiceHistoryServiceList(long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByPatient_HealthInsuranceNumber(healthInsuranceNumber) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
        }
        return repository.findMedicalServiceHistoriesByPatient_HealthInsuranceNumber(healthInsuranceNumber);
    }

    public List<MedicalServiceHistory> getDoctorMedicalServiceHistoryServiceList(long id) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByDoctor_Id(id) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Doctor ID: " + id);
        }
        return repository.findMedicalServiceHistoriesByDoctor_Id(id);
    }

    public List<MedicalServiceHistory> getServiceMedicalServiceHistoryServiceList(String name) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByServiceName(name) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Service Name: " + name);
        }
        return repository.findMedicalServiceHistoriesByServiceName(name);
    }

    public List<MedicalServiceHistory> getMedicalServiceHistoryServiceListForDate(Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByServiceDate(date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Date: " + date);
        }
        return repository.findMedicalServiceHistoriesByServiceDate(date);
    }
}