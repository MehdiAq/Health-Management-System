package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors/list")
    public String viewDoctors(Model model){
        List<Doctor> doctorList = doctorService.getDoctorList();
        model.addAttribute("allDoctors", doctorList);
        return "doctors";
    }

    @GetMapping("/doctors/{name}/list")
    public String viewDoctorsByName(@PathVariable(value = "name")String name, Model model) throws RecordNotFoundException {
        List<Doctor> doctorList = doctorService.getDoctorListByName(name);
        model.addAttribute("allDoctors", doctorList);
        return "service-histories";
    }

    @GetMapping("/doctors/new")
    public String addNewDoctor(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "new-doctor";
    }

    @PostMapping("/doctors/save")
    public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor saveDoctor, BindingResult result){
        if (result.hasErrors()){
            return "new-doctor";
        }
        doctorService.saveOrUpdateDoctor(saveDoctor);
        return "redirect:/doctors/list";
    }

    @GetMapping("/doctors/update/{id}")
    public String updateDoctor(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Doctor dbDoctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", dbDoctor);
        return "update-doctor";
    }

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctorWithId(@PathVariable(value = "id") long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

}