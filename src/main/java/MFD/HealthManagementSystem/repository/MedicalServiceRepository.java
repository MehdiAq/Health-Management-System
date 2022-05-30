package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, MedicalServiceId> {
    void deleteMedicalServiceHistoryByPatient_IdAndServiceNameAndServiceDate(Long id, String serviceName, Date date);
    MedicalService findByPatient_IdAndServiceNameAndServiceDate(Long id, String serviceName, Date date);
    List<MedicalService> findMedicalServicesByPatient_Id(Long healthInsuranceNumber);
    List<MedicalService> findMedicalServicesByDoctor_Id(Long doctorId);
    List<MedicalService> findMedicalServicesByServiceName(String serviceName);
    List<MedicalService> findMedicalServicesByServiceDate(Date date);
}