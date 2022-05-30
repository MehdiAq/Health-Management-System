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

    private final MedicalServiceRepository medRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public AppointmentService(AppointmentRepository appointmentRepository, MedicalServiceRepository medRepository) {
        this.repository = appointmentRepository;
        this.medRepository = medRepository;
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
//        Optional<Appointment> appointment =  repository.findById(id);
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        else{
            throw new RecordNotFoundException("Appointment with ID: " + id +" not found");
        }
    }

    public Appointment saveOrUpdateAppointment(Appointment appointment) throws RecordAlreadyExistsException{
        if(appointment.getId() == null){
            Appointment apt = repository.findAppointmentByDoctorAndAppointmentDateAndTimeSlot(appointment.getDoctor(), appointment.getAppointmentDate(), appointment.getTimeSlot());
            if(apt != null) {
                if (apt.getProcedure().equals(appointment.getProcedure())) {
                    throw new RecordAlreadyExistsException("That Timeslot is already booked with that Doctor");
                }
                appointment = updateAppointment(apt, appointment);
            }
            repository.save(appointment);
            medRepository.save(new MedicalService(appointment.getPatient(), appointment.getProcedure(), appointment.getAppointmentDate(), appointment.getDoctor()));
//                MedicalService toSave = new MedicalService();
//                toSave.setPatient(appointment.getPatient());
//                toSave.setServiceName(appointment.getProcedure());
//                toSave.setServiceDate(appointment.getAppointmentDate());
//                toSave.setDoctor(appointment.getDoctor());
//            medRepository.save(toSave);
            return appointment;
        }
        else{
            Optional<Appointment> appointment1 = repository.findById(appointment.getId());
            if (appointment1.isPresent()) {
                Appointment appointment2 = appointment1.get();
                appointment2.setId(appointment.getId());
                appointment2.setDoctor(appointment.getDoctor());
                appointment2.setAppointmentDate(appointment.getAppointmentDate());
                appointment2.setTimeSlot(appointment.getTimeSlot());
                appointment2.setPatient(appointment.getPatient());
                appointment2.setProcedure(appointment.getProcedure());
                appointment2 = repository.save(appointment2);
                return appointment2;

            }
            else{
                medRepository.save(new MedicalService(appointment.getPatient(), appointment.getProcedure(), appointment.getAppointmentDate(), appointment.getDoctor()));
                repository.save(appointment);
                return appointment;
            }
        }
    }

    private Appointment updateAppointment(Appointment apt, Appointment appointment) {
        Optional<Appointment> appointment1 = repository.findById(apt.getId());
        if (appointment1.isPresent()) {
            Appointment appointment2 = appointment1.get();
            appointment2.setProcedure(appointment.getProcedure());
            appointment2.setDoctor(appointment.getDoctor());
            appointment2.setAppointmentDate(appointment.getAppointmentDate());
            appointment2.setTimeSlot(appointment.getTimeSlot());
            appointment2.setPatient(appointment.getPatient());
            return appointment2;
        }
        return appointment;
    }

    public void deleteAppointment(Long id) {
        repository.deleteById(id);
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

    public void updateMedicalService(MedicalService medicalService, Appointment appointment){
        MedicalService toUpdate = medicalService;

        toUpdate.setPatient(appointment.getPatient());
        toUpdate.setDoctor(appointment.getDoctor());
        toUpdate.setServiceDate(appointment.getAppointmentDate());
        toUpdate.setServiceName(appointment.getProcedure());
        medRepository.delete(medicalService);
        medRepository.save(toUpdate);
    }
}