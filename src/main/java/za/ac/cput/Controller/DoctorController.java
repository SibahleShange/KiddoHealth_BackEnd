package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Service.DoctorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
 @PostMapping("/create")
// public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
//     Doctor savedDoctor = doctorService.create(doctor);
//     return ResponseEntity.ok(savedDoctor);
// }
 public Doctor create(@RequestBody Doctor doctor) {
        return doctorService.create(doctor);
 }

// @GetMapping("/read/{userId}")
//    public Doctor read(@PathVariable Long userId){
//        return doctorService.read(userId);
// }
@GetMapping("/read/{userId}")
public ResponseEntity<Doctor> read(@PathVariable Long userId) {
    Doctor doctor = doctorService.read(userId);
    if (doctor == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(doctor);
}


    @PutMapping("/update")
    public Doctor update(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
  }
  @GetMapping("/getAll")
    public List<Doctor> getAll(){
        return doctorService.getAll();
  }

    @PostMapping("/login")
    public Doctor login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String role = loginRequest.get("role");

        Doctor doctor = doctorService.findByEmail(email);
        if (doctor == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!doctor.getPassword().equals(password) ||
                !doctor.getRole().toString().equalsIgnoreCase(role)) {
            throw new RuntimeException("Invalid credentials");
        }

        doctor.setPassword(null);
        return doctor;
    }

    // Get doctor by email logic for forgot password
    @GetMapping("/byEmail/{email}")
    public Doctor getByEmail(@PathVariable String email) {
        Doctor doctor = doctorService.findByEmail(email.trim());
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        doctor.setPassword(null);
        return doctor;
    }

    // Update doctor password
    @PutMapping("/updatePassword/{userId}")
    public Doctor updatePassword(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        return doctorService.updatePassword(userId, newPassword);
    }



}


