package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;

@Repository
public interface PrescriptionHistoryRepository extends JpaRepository<PrescriptionHistory, PrescriptionHistoryId> {
    List<PrescriptionHistory> findPrescriptionHistoriesByPatient_HealthInsuranceNumber(long healthInsuranceNumber);
    List<PrescriptionHistory> findPrescriptionHistoriesByMedicationName (String medicationName);
    List<PrescriptionHistory> findPrescriptionHistoriesByDateOfPrescription(Date date);
}