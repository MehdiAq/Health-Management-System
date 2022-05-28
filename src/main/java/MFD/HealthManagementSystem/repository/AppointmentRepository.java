package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.sql.Date;
import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByPatient_HealthInsuranceNumber(Long healthInsuranceNumber, Pageable pageable);
    Page<Appointment> findByDoctor_Id(Long id, Pageable pageable);
    Appointment findAppointmentByDoctorAndAppointmentDateAndAppointmentTime(Doctor doctor, Date date, java.util.Date time);
//    Optional<Appointment> findByInsuranceAndAppointmentId(Long id, Long appointmentId);
}