package MFD.HealthManagementSystem.repository;


import MFD.HealthManagementSystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//    Page<Appointment> findByPatientId(Long healthInsuranceNumber, Pageable pageable);
//    //Optional<Appointment> findByInsuranceAndAppointmentId(Long id, Long appointmentId);
}