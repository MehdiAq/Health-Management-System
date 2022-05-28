package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/createAppointment")
    public String createAppointment(Model model){
        Appointment appointment = new Appointment();
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
