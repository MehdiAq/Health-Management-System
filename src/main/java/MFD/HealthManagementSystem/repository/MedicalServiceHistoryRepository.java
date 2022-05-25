package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface MedicalServiceHistoryRepository extends JpaRepository<MedicalServiceHistory, Long> {
}