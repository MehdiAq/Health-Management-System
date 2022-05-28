package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import MFD.HealthManagementSystem.service.AppointmentService;
import MFD.HealthManagementSystem.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PatientController {

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    private final ObjectMapper mapper = new ObjectMapper();

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Patient> patientList = patientService.getPatientList();
        model.addAttribute("allPatients", patientList);
        return "index";
    }

    @GetMapping("/addNewPatient")
    public String addNewPatient(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "new-patient";
    }

    @GetMapping("/patientDashboard/{id}")
    public String goToPatientDashboard(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException{
        Patient dbPatient = patientService.getPatientById(id);
        List<Appointment> upComingAppointments = appointmentService.getUpcomingAppointmentById(id);

        model.addAttribute("patient", dbPatient);
        model.addAttribute("appointments", upComingAppointments);
        return "dashboard";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid @ModelAttribute("patient") Patient savePatient, BindingResult result){
        if (result.hasErrors()){
            return "new-patient";
        }
        patientService.saveOrUpdatePatient(savePatient);

        return "redirect:/";
    }

    @GetMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Patient dbPatient = patientService.getPatientById(id);
        model.addAttribute("patient", dbPatient);
        return "update-patient";
    }

    @GetMapping("/deletePatient/{id}")
    public String deleteWithId(@PathVariable(value = "id") long id){
        patientService.deletePatient(id);
        return "redirect:/";
    }

}
