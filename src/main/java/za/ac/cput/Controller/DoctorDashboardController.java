package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.DoctorDashboard;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Service.DoctorService;
import java.util.List;

@RestController
@RequestMapping("/doctor/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorDashboardController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/summary/{doctorId}")
    public DoctorDashboard getDashboardSummary(@PathVariable Long doctorId) {
        return doctorService.getDashboardSummary(doctorId);
    }

    @GetMapping("/today-appointments/{doctorId}")
    public List<Appointment> getTodaysAppointments(@PathVariable Long doctorId) {
        return doctorService.getTodaysAppointments(doctorId);
    }
}