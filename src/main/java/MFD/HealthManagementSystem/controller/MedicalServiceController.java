package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordAlreadyExistsException;
import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.MedicalService;
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
import java.util.Date;
import java.util.List;

@Controller
public class MedicalServiceController {

    private final MedicalServiceService medicalServiceService;

    private final PatientService patientService;

    private final AppointmentService appointmentService;

    public MedicalServiceController(MedicalServiceService medicalServiceService, PatientService patientService, AppointmentService appointmentService) {
        this.medicalServiceService = medicalServiceService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/serviceHistory/patient/{patIn}")
    public String viewMedServicesByPatient(@PathVariable(value = "patIn")Long healthInsuranceNumber, Model model) throws RecordNotFoundException {
        List<MedicalService> medicalServiceHistories = medicalServiceService.getPatientMedicalServiceHistoryServiceList(healthInsuranceNumber);
        List<Appointment> upComingAppointments = appointmentService.getUpcomingAppointmentById(healthInsuranceNumber);

        model.addAttribute("numberOfServices", medicalServiceService.getPatientMedicalServiceHistoryServiceList(healthInsuranceNumber).size());
        model.addAttribute("numberOfAppointments", upComingAppointments.size());
        model.addAttribute("numberOfProfiles", patientService.getPatientList().size());
        model.addAttribute("numberOfProcedures", medicalServiceService.getMedicalServiceHistoryList().size());
        model.addAttribute("appointments", upComingAppointments);
        model.addAttribute("patient", patientService.getPatientById(healthInsuranceNumber));
        model.addAttribute("medicalServices", medicalServiceHistories);
        return "medical-service-history";
    }

    @GetMapping("/serviceHistory/doctor/{doctorId}/list")
    public String viewMedServicesByDoctor(@PathVariable(value = "doctorId")Long id, Model model) throws RecordNotFoundException {
        List<MedicalService> medicalServiceHistories = medicalServiceService.getDoctorMedicalServiceHistoryServiceList(id);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "medical-service-history";
    }

    @GetMapping("/serviceHistory/service/{serviceName}/list")
    public String viewMedServicesByServiceName(@PathVariable(value = "serviceName")String name, Model model) throws RecordNotFoundException {
        List<MedicalService> medicalServiceHistories = medicalServiceService.getServiceMedicalServiceHistoryServiceList(name);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "medical-service-history";
    }
    @GetMapping("/serviceHistory/date/{date}/list")
    public String viewMedServicesByDate(@PathVariable(value = "date")Date date, Model model) throws RecordNotFoundException {
        List<MedicalService> medicalServiceHistories = medicalServiceService.getMedicalServiceHistoryServiceListForDate(date);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "medical-service-history";
    }

    @GetMapping("/serviceHistory/new")
    public String addNewMedService(Model model){
        MedicalService medicalService = new MedicalService();
        model.addAttribute("medicalServiceHistory", medicalService);
        return "new-service-history";
    }

    @PostMapping("/serviceHistory/save")
    public String saveMedService(@Valid @ModelAttribute("medicalServiceHistory") MedicalService saveService, BindingResult result){
        if (result.hasErrors()){
            return "new-service-history";
        }
        medicalServiceService.saveOrUpdateMedService(saveService);
        return "redirect:/serviceHistory/list";
    }

    @GetMapping("/serviceHistory/update/{insuranceNumber}/{serviceName}/{date}")
    public String updateMedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber, @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") Date date, Model model) throws RecordNotFoundException, RecordAlreadyExistsException {
        MedicalService dbMedicalService = medicalServiceService.getMedicalServiceHistory(healthInsuranceNumber, serviceName, date);
        model.addAttribute("medicalServiceHistory", dbMedicalService);
        return "update-medical-service";
    }

    @GetMapping("/serviceHistory/delete/{insuranceNumber}/{serviceName}/{date}")
    public String deleteMedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber , @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") Date date) throws RecordNotFoundException{
        medicalServiceService.deleteServiceHistoryByPatientAndServiceAndDate(healthInsuranceNumber, serviceName, date);
        return "redirect:/medical-service-history";
    }
}