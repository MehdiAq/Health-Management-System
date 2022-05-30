package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import java.util.*;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, MedicalServiceId> {
    void deleteMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(Long healthInsuranceNumber, String serviceName, Date date);
    MedicalService findMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(Long healthInsuranceNumber, String serviceName, Date date);
    List<MedicalService> findMedicalServiceHistoriesByPatient_Id(Long healthInsuranceNumber);
    List<MedicalService> findMedicalServiceHistoriesByDoctor_Id(Long doctorId);
    List<MedicalService> findMedicalServiceHistoriesByServiceName(String serviceName);
    List<MedicalService> findMedicalServiceHistoriesByServiceDate(Date date);
}