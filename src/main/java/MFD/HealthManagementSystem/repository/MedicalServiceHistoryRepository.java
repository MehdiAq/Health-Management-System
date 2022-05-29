package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public interface MedicalServiceHistoryRepository extends JpaRepository<MedicalServiceHistory, MedicalServiceHistoryId> {
    void deleteMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(long healthInsuranceNumber, String serviceName, Date date);
    MedicalServiceHistory findMedicalServiceHistoryByPatient_HealthInsuranceNumberAndServiceNameAndServiceDate(long healthInsuranceNumber, String serviceName, Date date);
    List<MedicalServiceHistory> findMedicalServiceHistoriesByPatient_HealthInsuranceNumber(long healthInsuranceNumber);
    List<MedicalServiceHistory> findMedicalServiceHistoriesByDoctor_DoctorId(long doctorId);
    List<MedicalServiceHistory> findMedicalServiceHistoriesByServiceName(String serviceName);
    List<MedicalServiceHistory> findMedicalServiceHistoriesByServiceDate(Date date);
}