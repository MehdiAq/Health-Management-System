package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public String homePage(Model model){
        List<Patient> patientList = patientRepository.findAll();
        model.addAttribute("allPatients", patientList);
        return "index";
    }

    @GetMapping("/addNew")
    public String addNewPatient(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "new-patient";
    }

    @PostMapping("/save")
    public String savePatient(@Valid @ModelAttribute("patient") Patient savePatient, BindingResult result){
        if (result.hasErrors()){
            return "new-patient";
        }
        patientRepository.save(savePatient);

        return "redirect:/";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) throws RecordNotFoundException {
        Patient dbPatient = patientRepository.getReferenceById(id);
        model.addAttribute("patient", dbPatient);
        return "update-patient";
    }

    @GetMapping("/deletePatient/{id}")
    public String deleteWithId(@PathVariable(value = "id") long id){
        patientRepository.deleteById(id);
        return "redirect:/";
    }

}