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
        if(repository.findByPatientAndServiceNameAndServiceDate(history.getPatient(), history.getServiceName(), history.getServiceDate()) == null){
            repository.save(history);
            return history;
        }
        else{
            MedicalService history1 = repository.findByPatientAndServiceNameAndServiceDate(history.getPatient(), history.getServiceName(), history.getServiceDate());
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

    public void deleteServiceHistoryByPatientAndServiceAndDate(Patient patient, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findByPatientAndServiceNameAndServiceDate(patient, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Name: " + patient.getFirstName() + ' ' + patient.getLastName() + " --- Service: " + serviceName + " --- Date: " + date);
        }
        repository.deleteMedicalServiceHistoryByPatientAndServiceNameAndServiceDate(patient, serviceName, date);
    }


    public MedicalService getMedicalServiceHistory(Patient patient, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findByPatientAndServiceNameAndServiceDate(patient, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Name: " + patient.getFirstName() + ' ' + patient.getLastName() + " --- Service: " + serviceName + " --- Date: " + date);
        }
        return repository.findByPatientAndServiceNameAndServiceDate(patient, serviceName, date);
    }

    public List<MedicalService> getPatientMedicalServiceHistoryServiceList(Long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findMedicalServicesByPatient_Id(healthInsuranceNumber).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
        }
        return repository.findMedicalServicesByPatient_Id(healthInsuranceNumber);
    }

    public List<MedicalService> getDoctorMedicalServiceHistoryServiceList(Long id) throws RecordNotFoundException {
        if(repository.findMedicalServicesByDoctor_Id(id).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Doctor ID: " + id);
        }
        return repository.findMedicalServicesByDoctor_Id(id);
    }

    public List<MedicalService> getServiceMedicalServiceHistoryServiceList(String name) throws RecordNotFoundException {
        if(repository.findMedicalServicesByServiceName(name).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Service Name: " + name);
        }
        return repository.findMedicalServicesByServiceName(name);
    }

    public List<MedicalService> getMedicalServiceHistoryServiceListForDate(Date date) throws RecordNotFoundException {
        if(repository.findMedicalServicesByServiceDate(date).isEmpty()){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Date: " + date);
        }
        return repository.findMedicalServicesByServiceDate(date);
    }
}