package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.jpa.repository.*;

import java.time.*;
import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatient_HealthInsuranceNumber(Long healthInsuranceNumber);
    List<Appointment> findAllByDoctor_DoctorId(Long id);
    Appointment findAppointmentByDoctorAndAppointmentDateAndTimeSlot(Doctor doctor, LocalDate date, TimeSlot time);
//    List<Appointment> findAppointmentsByAppointmentDate(Date date);
}