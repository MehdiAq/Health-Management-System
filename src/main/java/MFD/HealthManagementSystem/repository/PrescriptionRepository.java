package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, PrescriptionId> {
    List<Prescription> findPrescriptionHistoriesByPatient_Id(Long healthInsuranceNumber);
    List<Prescription> findPrescriptionHistoriesByMedicationName (String medicationName);
    List<Prescription> findPrescriptionHistoriesByDateOfPrescription(Date date);
}