package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.service.AppointmentService;
import MFD.HealthManagementSystem.service.MedicalServiceService;
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
public class PatientController {

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    private final MedicalServiceService medicalService;

    public PatientController(PatientService patientService, AppointmentService appointmentService, MedicalServiceService medicalService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.medicalService = medicalService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allPatients", patientService.getPatientList());
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
        List<Appointment> upComingAppointments = appointmentService.getUpcomingAppointmentById(id);

        model.addAttribute("numberOfServices", medicalService.getPatientMedicalServiceHistoryServiceList(id).size());
        model.addAttribute("numberOfAppointments", upComingAppointments.size());
        model.addAttribute("numberOfProfiles", patientService.getPatientList().size());
        model.addAttribute("numberOfProcedures", appointmentService.getPatientAppointments(id).size());
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("appointments", upComingAppointments);
        return "dashboard";
    }

    @PostMapping("/patients/save")
    public String savePatient(@Valid @ModelAttribute("patient") Patient savePatient, BindingResult result){
        if (result.hasErrors()){
            return "new-patient";
        }
        patientService.saveOrUpdatePatient(savePatient);

        return "redirect:/";
    }

    @GetMapping("/updatePatient/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Patient dbPatient = patientService.getPatientById(id);
        model.addAttribute("patient", dbPatient);
        return "update-patient";
    }

    @GetMapping("/deletePatient/{id}")
    public String deleteWithId(@PathVariable(value = "id") Long id){
        patientService.deletePatient(id);
        return "redirect:/";
    }

}