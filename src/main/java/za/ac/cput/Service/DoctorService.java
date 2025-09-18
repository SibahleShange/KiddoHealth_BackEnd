package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Repository.DoctorRepository;

import java.util.List;
@Service
public class DoctorService implements IDoctorService {
  
 private  DoctorRepository doctorRepository;
 
@Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor read(Long id) {
        return this.doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }
    @Override
    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor findByEmail(String email) {
        return this.doctorRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    @Override
    public Doctor updatePassword(Long userId, String newPassword) {
        // Fetch doctor directly from repository
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Update password
        doctor.setPassword(newPassword);

        // Save changes directly
        return doctorRepository.save(doctor);
    }
}
