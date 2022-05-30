package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.DoctorRepository;
import MFD.HealthManagementSystem.service.AppointmentService;
import MFD.HealthManagementSystem.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, DoctorRepository doctorRepository, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorRepository = doctorRepository;
        this.patientService = patientService;
    }

    @GetMapping("/createAppointment/{id}")
    public String createAppointment(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Appointment appointment = new Appointment();
        Patient dbPatient = patientService.getPatientById(id);
        appointment.setPatient(dbPatient);
        List<Doctor> allDoctors = doctorRepository.findAll();
        model.addAttribute("patient", dbPatient);
        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", appointment);
        return "new-appointment";
    }

    @PostMapping("/saveAppointment/{id}")
    public String saveAppointment(@Valid @ModelAttribute("appointment") @PathVariable(value = "id") Long id, Appointment saveAppointment, BindingResult result) throws RecordNotFoundException {
        if (result.hasErrors()){
            return "new-appointment";
        }
        appointmentService.saveOrUpdateAppointment(saveAppointment);

        return "redirect:/patientDashboard/{id}";
    }

    @GetMapping("/updateAppointment/{id}")
    public String updateAppointment(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Appointment dbAppointment = appointmentService.getAppointmentById(id);
        model.addAttribute("patient", dbAppointment);
        return "update-patient";
    }

    @GetMapping("/deleteAppointment/{id}")
    public String deleteWithId(@PathVariable(value = "id") long id){
        patientService.deletePatient(id);
        return "redirect:/";
    }
}
