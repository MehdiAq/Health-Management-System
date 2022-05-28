package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long healthInsuranceNumber);
//    //Optional<Appointment> findByInsuranceAndAppointmentId(Long id, Long appointmentId);
}