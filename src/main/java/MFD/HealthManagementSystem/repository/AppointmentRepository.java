package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.sql.Date;
import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatient_HealthInsuranceNumber(Long healthInsuranceNumber);
    List<Appointment> findAllByDoctor_Id(Long id);
    Appointment findAppointmentByDoctorAndAppointmentDateAndAppointmentTime(Doctor doctor, Date date, java.util.Date time);
//    Optional<Appointment> findByInsuranceAndAppointmentId(Long id, Long appointmentId);
}