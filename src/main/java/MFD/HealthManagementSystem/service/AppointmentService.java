package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointmentList() {
        List<Appointment> fetchData = appointmentRepository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public List<Appointment> getDoctorAppointments(long id, Pageable pageable) {
        Page<Appointment> fetchData = appointmentRepository.findByDoctor_Id(id, pageable);
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public List<Appointment> getPatientAppointments(long id, Pageable pageable) {
        Page<Appointment> fetchData = appointmentRepository.findByPatient_HealthInsuranceNumber(id, pageable);
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public Appointment getAppointmentById(long id) throws RecordNotFoundException {
        Optional<Appointment> appointment =  appointmentRepository.findById(id);
        if(appointment.isPresent()){
            return mapper.convertValue(appointment.get(), Appointment.class);
        }
        else{
            throw new RecordNotFoundException("Appointment with ID: " + id +" not found");
        }
    }

    public Appointment saveOrUpdateAppointment(Appointment appointment) throws RecordAlreadyExistsException{
        if(appointment.getAppointmentId() == null){
            Appointment db = appointmentRepository.findAppointmentByDoctorAndAppointmentDateAndAppointmentTime(appointment.getDoctor(), appointment.getAppointmentDate(), appointment.getAppointmentTime());
            if(db != null){
                throw new RecordAlreadyExistsException("That Timeslot is already booked with that Doctor");
            }
            appointmentRepository.save(appointment);
            return appointment;
        }
        else{
            Optional<Appointment> appointment1 = appointmentRepository.findById(appointment.getAppointmentId());
            if (appointment1.isPresent()) {
                Appointment appointment2 = appointment1.get();
                appointment2.setAppointmentId(appointment.getAppointmentId());
                appointment2.setDoctor(appointment.getDoctor());
                appointment2.setAppointmentDate(appointment.getAppointmentDate());
                appointment2.setAppointmentTime(appointment.getAppointmentTime());
                appointment2.setPatient(appointment.getPatient());
                appointment2 = appointmentRepository.save(appointment2);

                return appointment2;
            }
            else{
                appointmentRepository.save(appointment);
                return appointment;
            }
        }
    }

    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}