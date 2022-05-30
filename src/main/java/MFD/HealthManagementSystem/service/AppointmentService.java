package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import MFD.HealthManagementSystem.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository repository;

    private final ObjectMapper mapper= new ObjectMapper();

    public List<Appointment> getAppointmentListByPatientId() {
        List<Appointment> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public Patient getAppointmentById(long id) throws RecordNotFoundException {
        Optional<Appointment> appointment =  repository.findById(id);
        if(appointment.isPresent()){
            return mapper.convertValue(appointment.get(), Patient.class);
        }
        else{
            throw new RecordNotFoundException("There is no patient");
        }
    }

    public List<Appointment> getUpcomingAppointmentById(Long id) {
        List<Appointment> appointments = repository.findByPatientId(id);
        List<Appointment> upComingAppointments = new ArrayList<>();
        java.util.Date date = new java.util.Date();

        for(Appointment app : appointments) {
            if (app.getAppointmentDate().after(date)) {
                upComingAppointments.add(app);
            }
        }
        return upComingAppointments;
    }

    public Patient setPatientId(Long id) throws RecordNotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            return mapper.convertValue(patient.get(), Patient.class);
        }
        else{
            throw new RecordNotFoundException("There is no patient");
        }
    }

    public Appointment saveOrUpdateAppointment(Appointment appointment){
        if(appointment.getAppointmentId() == null){
            repository.save(appointment);
            return appointment;
        }
        else{
            Optional<Appointment> appointment1 = repository.findById(appointment.getAppointmentId());
            if (appointment1.isPresent()) {
                Appointment appointment2 = appointment1.get(); //the data that I fetched from db
                appointment2.setAppointmentId(appointment.getAppointmentId());
                appointment2.setProcedure(appointment.getProcedure());
                appointment2.setAppointmentDate(appointment.getAppointmentDate());
                appointment2.setTimeSlots(appointment.getTimeSlots());
                appointment2 = repository.save(appointment2);

                return appointment2;
            }
            else{
                repository.save(appointment);
                return appointment;
            }
        }
    }
}
