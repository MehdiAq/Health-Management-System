package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
public class PatientController {

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    private final MedicalServiceService medicalService;

    private final ObjectMapper mapper = new ObjectMapper();

    public PatientController(PatientService patientService, AppointmentService appointmentService, MedicalServiceService medicalService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.medicalService = medicalService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("allPatients", patientService.getPatientList());
//        return "index";
//    }


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