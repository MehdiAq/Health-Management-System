package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.MedicalService;
import MFD.HealthManagementSystem.repository.MedicalServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        if(repository.findByPatient_IdAndServiceNameAndServiceDate(history.getPatient().getId(), history.getServiceName(), history.getServiceDate()) == null){
            repository.save(history);
            return history;
        }
        else{
            MedicalService history1 = repository.findByPatient_IdAndServiceNameAndServiceDate(history.getPatient().getId(), history.getServiceName(), history.getServiceDate());
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

    public void deleteServiceHistoryByPatientAndServiceAndDate(Long id, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findByPatient_IdAndServiceNameAndServiceDate(id, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + id + " --- Service: " + serviceName + " --- Date: " + date);
        }
        repository.deleteMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(id, serviceName, date);
    }


    public MedicalService getMedicalServiceHistory(Long id, String serviceName, Date date) throws RecordNotFoundException {
        if(repository.findByPatient_IdAndServiceNameAndServiceDate(id, serviceName, date) == null){
            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + id + " --- Service: " + serviceName + " --- Date: " + date);
        }
        return repository.findByPatient_IdAndServiceNameAndServiceDate(id, serviceName, date);
    }

    public List<MedicalService> getPatientMedicalServiceHistoryServiceList(Long healthInsuranceNumber) throws RecordNotFoundException {
        if(repository.findMedicalServicesByPatient_Id(healthInsuranceNumber).isEmpty()){
//            throw new RecordNotFoundException("No such Medical Service History Exists --- Patient Health Insurance Number: " + healthInsuranceNumber);
            return Collections.emptyList();
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