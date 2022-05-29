//package MFD.HealthManagementSystem.controller;
//
//import MFD.HealthManagementSystem.exception.*;
//import MFD.HealthManagementSystem.model.*;
//import MFD.HealthManagementSystem.repository.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.stereotype.*;
//import org.springframework.ui.*;
//import org.springframework.validation.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@Controller
//public class DoctorAvailabilityController {
//
//    @Autowired
//    private DoctorAvailabilityRepository doctorAvailabilityRepository;
//
////    @GetMapping("/availabilities/{doctorId}")
////    public String homePage(@PathVariable(value = "doctorId")long id, Model model) throws RecordNotFoundException {
////        List<DoctorAvailability> availabilityList = doctorAvailabilityRepository.findAllById(id);
////        model.addAttribute("allPatients", patientList);
////        return "availabilities-index";
////    }
//
//    @GetMapping("/addNewAvailability")
//    public String newAvailability(Model model){
//        DoctorAvailability availability = new DoctorAvailability();
//        model.addAttribute("availability", availability);
//        return "new-availability";
//    }
//
//    @PostMapping("/saveAvailability")
//    public String saveAvailability(@ModelAttribute("availability") DoctorAvailability saveAvailability, BindingResult result){
//        if (result.hasErrors()){
//            return "new-availability";
//        }
//        doctorAvailabilityRepository.save(saveAvailability);
//
//        return "redirect:/availabilities";
//    }
//}