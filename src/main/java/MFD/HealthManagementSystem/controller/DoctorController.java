package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.service.DoctorService;
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

//    @GetMapping("/doctors/{name}/list")
//    public String viewDoctorsByName(@PathVariable(value = "name")String name, Model model) throws RecordNotFoundException {
//        List<Doctor> doctorList = doctorService.getDoctorListByName(name);
//        model.addAttribute("allDoctors", doctorList);
//        return "doctors";
//    }

    @GetMapping("/doctors/{specialty}/list")
    public String viewDoctorsBySpecialty(@PathVariable(value = "specialty")String specialty, Model model) throws RecordNotFoundException {
        List<Doctor> doctorList = doctorService.getDoctorListBySpecialty(specialty);
        model.addAttribute("allDoctors", doctorList);
        return "doctors";
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
    public String deleteDoctorWithId(@PathVariable(value = "id") Long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

}