package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    private final ObjectMapper mapper= new ObjectMapper();

    public List<Appointment> getAppointmentListByPatientId() {
        List<Appointment> fetchData = repository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public List<Appointment> getUpcomingAppointmentById(Long id) {
        List<Appointment> appointments = repository.findByPatientId(id);
        List<Appointment> upComingAppointments = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for(Appointment app : appointments) {
            if (app.getAppointmentDate().isAfter(now)) {
                upComingAppointments.add(app);
            }
        }
        return upComingAppointments;
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
//                appointment2.setAppointmentName(appointment.getAppointmentName());
                appointment2.setAppointmentDate(appointment.getAppointmentDate());
//                appointment2.setAppointmentTime(appointment.getAppointmentTime());
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
