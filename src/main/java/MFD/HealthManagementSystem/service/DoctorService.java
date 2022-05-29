package MFD.HealthManagementSystem.service;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctorList() {
        List<Doctor> fetchData = doctorRepository.findAll();
        var result = mapper.convertValue(fetchData, List.class);
        return result;
    }

    public Doctor getDoctorById(long id) throws RecordNotFoundException {
        Optional<Doctor> doctor =  doctorRepository.findById(id);
        if(doctor.isPresent()){
            return mapper.convertValue(doctor.get(), Doctor.class);
        }
        else{
            throw new RecordNotFoundException("Doctor with ID: " + id +" not found");
        }
    }

    public Doctor saveOrUpdateDoctor(Doctor doctor){
        if(doctor.getDoctorId() == null){
            doctorRepository.save(doctor);
            return doctor;
        }
        else{
            Optional<Doctor> doctor1 = doctorRepository.findById(doctor.getDoctorId());
            if (doctor1.isPresent()) {
                Doctor doctor2 = doctor1.get();
                doctor2.setDoctorId(doctor.getDoctorId());
                doctor2.setFirstName(doctor.getFirstName());
                doctor2.setLastName(doctor.getLastName());
                doctor2.setAddress(doctor.getAddress());
                doctor2.setCellphone(doctor.getCellphone());
                doctor2.setSpecialty(doctor.getSpecialty());
                doctor2 = doctorRepository.save(doctor2);

                return doctor2;
            }
            else{
                doctorRepository.save(doctor);
                return doctor;
            }
        }
    }

    public void deleteDoctor(long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

//    public List<Doctor> getDoctorListByName(String name) throws RecordNotFoundException{
//        if(doctorRepository.findDoctorsByFirstNameContainsOrLastNameContains(name).isEmpty()) {
//            throw new RecordNotFoundException("No Doctors with name containing: " + name);
//        }
//        return doctorRepository.findDoctorsByFirstNameContainsOrLastNameContains(name);
//    }

    public List<Doctor> getDoctorListBySpecialty(String specialty) throws RecordNotFoundException{
        if(doctorRepository.findDoctorsBySpecialty(specialty).isEmpty()) {
            throw new RecordNotFoundException("No Doctors with " + specialty + " specialty");
        }
        return doctorRepository.findDoctorsBySpecialty(specialty);
    }
}