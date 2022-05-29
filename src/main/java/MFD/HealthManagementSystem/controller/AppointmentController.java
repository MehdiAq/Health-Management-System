package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.DoctorRepository;
import MFD.HealthManagementSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService, DoctorRepository doctorRepository) {
        this.appointmentService = appointmentService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/createAppointment")
    public String createAppointment(Model model){
        Appointment appointment = new Appointment();
        List<Doctor> allDoctors = doctorRepository.findAll();
        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", appointment);
        return "new-appointment";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment saveAppointment, BindingResult result){
        if (result.hasErrors()){
            return "new-appointment";
        }
        appointmentService.saveOrUpdateAppointment(saveAppointment);

        return "redirect:/patientDashboard";
    }
}
