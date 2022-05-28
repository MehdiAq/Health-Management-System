package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
