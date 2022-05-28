package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.sql.Date;
import java.util.*;

@Controller
public class MedicalServiceHistoryController {

    private final MedicalServiceHistoryService medicalServiceHistoryService;

    public MedicalServiceHistoryController(MedicalServiceHistoryService medicalServiceHistoryService) {
        this.medicalServiceHistoryService = medicalServiceHistoryService;
    }

    @GetMapping("/serviceHistory/list")
    public String viewMedServices(Model model){
        List<MedicalServiceHistory> medicalServiceHistories = medicalServiceHistoryService.getMedicalServiceHistoryServiceList();
        model.addAttribute("medHistories", medicalServiceHistories);
        return "service-histories";
    }

    @GetMapping("/serviceHistory/patient/{patientInsurance}/list")
    public String viewMedServicesByPatient(@PathVariable(value = "patientInsurance")long healthInsuranceNumber, Model model) throws RecordNotFoundException {
        List<MedicalServiceHistory> medicalServiceHistories = medicalServiceHistoryService.getPatientMedicalServiceHistoryServiceList(healthInsuranceNumber);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "service-histories";
    }

    @GetMapping("/serviceHistory/doctor/{doctorId}/list")
    public String viewMedServicesByDoctor(@PathVariable(value = "doctorId")long id, Model model) throws RecordNotFoundException {
        List<MedicalServiceHistory> medicalServiceHistories = medicalServiceHistoryService.getDoctorMedicalServiceHistoryServiceList(id);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "service-histories";
    }

    @GetMapping("/serviceHistory/service/{serviceName}/list")
    public String viewMedServicesByServiceName(@PathVariable(value = "serviceName")String name, Model model) throws RecordNotFoundException {
        List<MedicalServiceHistory> medicalServiceHistories = medicalServiceHistoryService.getServiceMedicalServiceHistoryServiceList(name);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "service-histories";
    }
    @GetMapping("/serviceHistory/date/{date}/list")
    public String viewMedServicesByDate(@PathVariable(value = "date")Date date, Model model) throws RecordNotFoundException {
        List<MedicalServiceHistory> medicalServiceHistories = medicalServiceHistoryService.getMedicalServiceHistoryServiceListForDate(date);
        model.addAttribute("medHistories", medicalServiceHistories);
        return "service-histories";
    }

    @GetMapping("/serviceHistory/new")
    public String addNewMedService(Model model){
        MedicalServiceHistory medicalServiceHistory = new MedicalServiceHistory();
        model.addAttribute("medicalServiceHistory", medicalServiceHistory);
        return "new-service-history";
    }

    @PostMapping("/serviceHistory/save")
    public String saveMedService(@Valid @ModelAttribute("medicalServiceHistory") MedicalServiceHistory saveService, BindingResult result){
        if (result.hasErrors()){
            return "new-service-history";
        }
        medicalServiceHistoryService.saveOrUpdateMedServiceHistory(saveService);
        return "redirect:/serviceHistory/list";
    }

    @GetMapping("/serviceHistory/update/{insuranceNumber}/{serviceName}/{date}")
    public String updatemedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber, @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") java.sql.Date date, Model model) throws RecordNotFoundException, RecordAlreadyExistsException {
        MedicalServiceHistory dbMedicalServiceHistory = medicalServiceHistoryService.getMedicalServiceHistory(healthInsuranceNumber, serviceName, date);
        model.addAttribute("medicalServiceHistory", dbMedicalServiceHistory);
        return "update-service-history";
    }

    @GetMapping("/serviceHistory/delete/{insuranceNumber}/{serviceName}/{date}")
    public String deleteMedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber , @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") java.sql.Date date) throws RecordNotFoundException{
        medicalServiceHistoryService.deleteServiceHistoryByPatientAndServiceAndDate(healthInsuranceNumber, serviceName, date);
        return "redirect:/service-histories";
    }
}