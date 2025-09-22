package za.ac.cput.Factory;

import za.ac.cput.Domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentFactory {

    public static Appointment createAppointment(
            Parent parent,
            Doctor doctor,
            Child child,
            LocalDate appointmentDate,
            LocalTime timeSlot,
            String note) {

        return new Appointment.Builder()
                .setParent(parent)
                .setDoctor(doctor)
                .setChild(child)
                .setAppointmentDate(appointmentDate)
                .setTimeSlot(timeSlot)
                .setNote(note)
                .setStatus(AppointmentStatus.SCHEDULED)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .build();
    }
}
