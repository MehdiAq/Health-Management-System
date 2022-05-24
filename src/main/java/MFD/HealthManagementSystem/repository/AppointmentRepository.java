package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByAppointmentId(Long healthInsuranceNumber, Pageable pageable);
//    Optional<Appointment> findByInsuranceAndAppointmentId(Long id, Long appointmentId);
}