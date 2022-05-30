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
public class MedicalServiceController {

    private final MedicalServiceService medicalServiceService;

    public MedicalServiceController(MedicalServiceService medicalServiceService) {
        this.medicalServiceService = medicalServiceService;
    }

    @GetMapping("/serviceHistory/list")
    public String viewMedServices(Model model){
        List<MedicalService> medicalServiceHistories = medicalServiceService.getMedicalServiceHistoryList();
        model.addAttribute("medHistories", medicalServiceHistories);
        return "medical-service-history";
    }

    @GetMapping("/serviceHistory/patient/{patientInsurance}/list")
    public String viewMedServicesByPatient(@PathVariable(value = "patientInsurance")Long healthInsuranceNumber, Model model) throws RecordNotFoundException {
        List<MedicalService> medicalServiceHistories = medicalServiceService.getPatientMedicalServiceHistoryServiceList(healthInsuranceNumber);
        model.addAttribute("medHistories", medicalServiceHistories);
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
    public String updateMedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber, @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") java.sql.Date date, Model model) throws RecordNotFoundException, RecordAlreadyExistsException {
        MedicalService dbMedicalService = medicalServiceService.getMedicalServiceHistory(healthInsuranceNumber, serviceName, date);
        model.addAttribute("medicalServiceHistory", dbMedicalService);
        return "update-medical-service";
    }

    @GetMapping("/serviceHistory/delete/{insuranceNumber}/{serviceName}/{date}")
    public String deleteMedService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber , @PathVariable(value = "serviceName")String serviceName, @PathVariable(value = "date") java.sql.Date date) throws RecordNotFoundException{
        medicalServiceService.deleteServiceHistoryByPatientAndServiceAndDate(healthInsuranceNumber, serviceName, date);
        return "redirect:/medical-service-history";
    }
}