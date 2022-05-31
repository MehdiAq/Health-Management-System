package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.Prescription;
import MFD.HealthManagementSystem.model.id.PrescriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, PrescriptionId> {
    List<Prescription> findPrescriptionHistoriesByPatient_Id(Long healthInsuranceNumber);
    List<Prescription> findPrescriptionHistoriesByMedicationName (String medicationName);
    List<Prescription> findPrescriptionHistoriesByDateOfPrescription(Date date);
}