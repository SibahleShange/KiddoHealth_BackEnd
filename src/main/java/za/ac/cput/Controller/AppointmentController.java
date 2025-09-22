package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Service.AppointmentService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {


    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment appointment) {
        return service.createAppointment(appointment);
    }

    @GetMapping("/read/{id}")
    public Appointment read(@PathVariable Long id) {
        return service.getAppointmentById(id);
    }

    @PutMapping("/update")
    public Appointment update(@RequestBody Appointment appointment) {
        return service.updateAppointment(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAppointment(id);
    }

    @GetMapping("/getAll")
    public List<Appointment> getAll() {
        return service.getAllAppointments();
    }

    @GetMapping("/byDoctor/{doctorId}/date/{date}")
    public List<Appointment> getByDoctorAndDate(
            @PathVariable Long doctorId,
            @PathVariable String date) {
        LocalDate appointmentDate = LocalDate.parse(date); //yyyy-MM-dd
        return service.getAppointmentsByDoctorAndDate(doctorId, appointmentDate);
    }



}
