package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.model.id.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PrescriptionHistoryRepository extends JpaRepository<PrescriptionHistory, PrescriptionHistoryId> {
}