package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Prescription;
import MFD.HealthManagementSystem.service.AppointmentService;
import MFD.HealthManagementSystem.service.MedicalServiceService;
import MFD.HealthManagementSystem.service.PatientService;
import MFD.HealthManagementSystem.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    private final MedicalServiceService medicalService;

    public PrescriptionController(PrescriptionService prescriptionService, PatientService patientService, AppointmentService appointmentService, MedicalServiceService medicalService) {
        this.prescriptionService = prescriptionService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.medicalService = medicalService;
    }

    @GetMapping("/prescriptionHistory/list")
    public String viewPrescriptionHistories(Model model){
        List<Prescription> prescriptionHistories = prescriptionService.getPrescriptionHistoryList();
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }

    @GetMapping("/prescriptionHistory/patient/{patIn}")
    public String viewPrescriptionHistoriesByPatient(@PathVariable(value = "patIn")Long healthInsuranceNumber, Model model) throws RecordNotFoundException {
        List<Prescription> prescriptionHistories = prescriptionService.getPatientPrescriptionHistoryList(healthInsuranceNumber);
        List<Appointment> upComingAppointments = appointmentService.getUpcomingAppointmentById(healthInsuranceNumber);
        model.addAttribute("numberOfServices", medicalService.getPatientMedicalServiceHistoryServiceList(healthInsuranceNumber).size());
        model.addAttribute("numberOfAppointments", upComingAppointments.size());
        model.addAttribute("numberOfProfiles", patientService.getPatientList().size());
        model.addAttribute("numberOfProcedures", appointmentService.getPatientAppointments(healthInsuranceNumber).size());
        model.addAttribute("patient", patientService.getPatientById(healthInsuranceNumber));
        model.addAttribute("appointments", upComingAppointments);
        model.addAttribute("patient", patientService.getPatientById(healthInsuranceNumber));
        model.addAttribute("prescriptions", prescriptionHistories);
        return "prescription-history";
    }

    @GetMapping("/prescriptionHistory/medication/{medName}/list")
    public String viewPrescriptionHistoriesByMedicationName(@PathVariable(value = "medName")String name, Model model) throws RecordNotFoundException {
        List<Prescription> prescriptionHistories = prescriptionService.getPrescriptionHistoryListByMedication(name);
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }
    @GetMapping("/prescriptionHistory/date/{date}/list")
    public String viewPrescriptionHistoriesByDate(@PathVariable(value = "date") Date date, Model model) throws RecordNotFoundException {
        List<Prescription> prescriptionHistories = prescriptionService.getPrescriptionHistoryListForDate(date);
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }

    @GetMapping("/prescriptionHistory/new")
    public String addNewPrescriptionHistory(Model model){
        Prescription history = new Prescription();
        model.addAttribute("history", history);
        return "new-prescription-history";
    }

    @PostMapping("/prescriptionHistory/save")
    public String savePrescriptionHistory(@Valid @ModelAttribute("medicalServiceHistory") Prescription saveService, BindingResult result){
        if (result.hasErrors()){
            return "new-prescription-history";
        }
        prescriptionService.savePrescriptionHistory(saveService);
        return "redirect:/prescriptionHistory/list";
    }
}