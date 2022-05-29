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
public class PrescriptionHistoryController {

    private final PrescriptionHistoryService service;

    public PrescriptionHistoryController(PrescriptionHistoryService service) {
        this.service = service;
    }

    @GetMapping("/prescriptionHistory/list")
    public String viewPrescriptionHistories(Model model){
        List<PrescriptionHistory> prescriptionHistories = service.getPrescriptionHistoryList();
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }

    @GetMapping("/prescriptionHistory/patient/{patientInsurance}/list")
    public String viewPrescriptionHistoriesByPatient(@PathVariable(value = "patientInsurance")long healthInsuranceNumber, Model model) throws RecordNotFoundException {
        List<PrescriptionHistory> prescriptionHistories = service.getPatientPrescriptionHistoryList(healthInsuranceNumber);
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }

    @GetMapping("/prescriptionHistory/medication/{medName}/list")
    public String viewPrescriptionHistoriesByMedicationName(@PathVariable(value = "medName")String name, Model model) throws RecordNotFoundException {
        List<PrescriptionHistory> prescriptionHistories = service.getPrescriptionHistoryListByMedication(name);
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }
    @GetMapping("/prescriptionHistory/date/{date}/list")
    public String viewPrescriptionHistoriesByDate(@PathVariable(value = "date") Date date, Model model) throws RecordNotFoundException {
        List<PrescriptionHistory> prescriptionHistories = service.getPrescriptionHistoryListForDate(date);
        model.addAttribute("prescriptionHistories", prescriptionHistories);
        return "prescription-histories";
    }

    @GetMapping("/prescriptionHistory/new")
    public String addNewPrescriptionHistory(Model model){
        PrescriptionHistory history = new PrescriptionHistory();
        model.addAttribute("history", history);
        return "new-prescription-history";
    }

    @PostMapping("/prescriptionHistory/save")
    public String savePrescriptionHistory(@Valid @ModelAttribute("medicalServiceHistory") PrescriptionHistory saveService, BindingResult result){
        if (result.hasErrors()){
            return "new-prescription-history";
        }
        service.savePrescriptionHistory(saveService);
        return "redirect:/prescriptionHistory/list";
    }
}