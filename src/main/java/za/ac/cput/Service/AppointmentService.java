package za.ac.cput.Service;


import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment createAppointment(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        return this.repository.save(appointment);
    }


    public Appointment getAppointmentById(Long id) {
        if (id == null) {
            return null;
        }
        return this.repository.findById(id).orElse(null);
    }


    public Appointment updateAppointment(Appointment appointment) {
        if (appointment == null || appointment.getAppointmentId() == null) {
            return null;
        }
        if (!this.repository.existsById(appointment.getAppointmentId())) {
            return null;
        }
        return this.repository.save(appointment);
    }

    public boolean deleteAppointment(Long id) {
        if (id == null || !this.repository.existsById(id)) {
            return false;
        }
        this.repository.deleteById(id);
        return true;
    }

    public List<Appointment> getAllAppointments() {
        return this.repository.findAll();
    }


    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate appointmentDate) {
        if (doctorId == null || appointmentDate == null) {
            return List.of();
        }
        return this.repository.findByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
    }

}
