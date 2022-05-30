package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;

@Service
public class MedicalServiceService {

    private final MedicalServiceRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();


    public MedicalServiceService(MedicalServiceRepository repository) {
        this.repository = repository;
    }

    public List<MedicalService> getMedicalServiceHistoryList() {
        List<MedicalService> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public MedicalService saveOrUpdateMedService(MedicalService history){
        if(repository.findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(history.getPatient().getId(), history.getServiceName(), history.getServiceDate()) == null){
            repository.save(history);
            return history;
        }
        else{
            MedicalService history1 = repository.findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(history.getPatient().getId(), history.getServiceName(), history.getServiceDate());
            if (history1 != null) {
                MedicalService history2 = history1;
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

    public void deleteServiceHistoryByPatientAndServiceAndDate(Long healthInsuranceNumber, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber + " --- Service: " + serviceName + " --- Date: " + date);
        }
        repository.deleteMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date);
    }


    public MedicalService getMedicalServiceHistory(Long healthInsuranceNumber, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber + " --- Service: " + serviceName + " --- Date: " + date);
        }
        return repository.findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(healthInsuranceNumber, serviceName, date);
    }

    public List<MedicalService> getPatientMedicalServiceHistoryServiceList(Long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByPatient_Id(healthInsuranceNumber).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
        }
        return repository.findMedicalServiceHistoriesByPatient_Id(healthInsuranceNumber);
    }

    public List<MedicalService> getDoctorMedicalServiceHistoryServiceList(Long id) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByDoctor_Id(id).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Doctor ID: " + id);
        }
        return repository.findMedicalServiceHistoriesByDoctor_Id(id);
    }

    public List<MedicalService> getServiceMedicalServiceHistoryServiceList(String name) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByServiceName(name).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Service Name: " + name);
        }
        return repository.findMedicalServiceHistoriesByServiceName(name);
    }

    public List<MedicalService> getMedicalServiceHistoryServiceListForDate(Date date) throws RecordNotFoundException {
        if(repository.findMedicalServiceHistoriesByServiceDate(date).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Date: " + date);
        }
        return repository.findMedicalServiceHistoriesByServiceDate(date);
    }
}