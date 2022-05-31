package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
//    List<Doctor> findDoctorsByFirstNameContainsOrLastNameContains(String name);
    List<Doctor> findDoctorsBySpecialty(String name);
}