package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.jpa.repository.*;


import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatient_Id(Long healthInsuranceNumber);
    List<Appointment> findAllByDoctor_Id(Long id);
    Appointment findAppointmentByDoctorAndAppointmentDateAndTimeSlot(Doctor doctor, Date date, String time);
//    List<Appointment> findAppointmentsByAppointmentDate(Date date);
}