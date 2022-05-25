package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
