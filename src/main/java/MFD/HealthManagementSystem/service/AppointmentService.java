package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;


import java.util.*;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.repository = appointmentRepository;
    }

    public List<Appointment> getAppointmentList() {
        List<Appointment> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public List<Appointment> getDoctorAppointments(Long id) {
        List<Appointment> fetchData = repository.findAllByDoctor_Id(id);
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public List<Appointment> getPatientAppointments(Long id) {
        List<Appointment> fetchData = repository.findAllByPatient_Id(id);
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public Appointment getAppointmentById(Long id) throws RecordNotFoundException {
        Optional<Appointment> appointment =  repository.findById(id);
        if(appointment.isPresent()){
            return mapper.convertValue(appointment.get(), Appointment.class);
        }
        else{
            throw new RecordNotFoundException("Appointment with ID: " + id +" not found");
        }
    }

    public Appointment saveOrUpdateAppointment(Appointment appointment) throws RecordAlreadyExistsException{
        if(appointment.getAppointmentId() == null){
            Appointment db = repository.findAppointmentByDoctorAndAppointmentDateAndTimeSlot(appointment.getDoctor(), appointment.getAppointmentDate(), appointment.getTimeSlot());
            if(db != null){
                throw new RecordAlreadyExistsException("That Timeslot is already booked with that Doctor");
            }
            repository.save(appointment);
            return appointment;
        }
        else{
            Optional<Appointment> appointment1 = repository.findById(appointment.getAppointmentId());
            if (appointment1.isPresent()) {
                Appointment appointment2 = appointment1.get();
                appointment2.setAppointmentId(appointment.getAppointmentId());
                appointment2.setDoctor(appointment.getDoctor());
                appointment2.setAppointmentDate(appointment.getAppointmentDate());
                appointment2.setTimeSlot(appointment.getTimeSlot());
                appointment2.setPatient(appointment.getPatient());
                appointment2 = repository.save(appointment2);

                return appointment2;
            }
            else{
                repository.save(appointment);
                return appointment;
            }
        }
    }

    public void deleteAppointment(Long appointmentId) {
        repository.deleteById(appointmentId);
    }

//    public List<Appointment> getAppointmentListByDate(Date date) {
//        return repository.findAppointmentsByAppointmentDate(date);
//    }
    public List<Appointment> getUpcomingAppointmentById(Long id) {
        List<Appointment> appointments = repository.findAllByPatient_Id(id);
        List<Appointment> upComingAppointments = new ArrayList<>();
        Date date = new Date();

        for(Appointment app : appointments) {
            if (app.getAppointmentDate().after(date)) {
                upComingAppointments.add(app);
            }
        }
        return upComingAppointments;
    }
}