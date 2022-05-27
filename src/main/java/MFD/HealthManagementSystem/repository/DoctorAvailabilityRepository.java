package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.DoctorAvailability;
import MFD.HealthManagementSystem.model.id.DoctorAvailabilityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, DoctorAvailabilityId> {
}
