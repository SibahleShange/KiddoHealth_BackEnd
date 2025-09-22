package za.ac.cput.Factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Domain.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentFactoryTest {



    private Parent parent;
    private Doctor doctor;
    private Child child;

    @BeforeEach
    void setUp() {
        // Dummy objects (replace with your own builders/factories if available)
        parent = new Parent.Builder()
                .setUserId(1L)
                .setName("John Doe")
                .setEmail("john@example.com")
                .build();

        doctor = new Doctor.Builder()
                .setUserId(1L)
                .setName("Dr. Smith")
                .setSpecialization("Pediatrics")
                .build();

        child = new Child.Builder()
                .setChildId(1L)
                .setName("Baby Doe")
                .setAge(2)
                .build();
    }

    @Test
    void testCreateAppointmentSuccess() {
        LocalDate appointmentDate = LocalDate.of(2025, 10, 10);
        LocalTime timeSlot = LocalTime.of(10, 30);

        Appointment appointment = AppointmentFactory.createAppointment(
                parent, doctor, child, appointmentDate, timeSlot, "First checkup"
        );

        assertNotNull(appointment);
        assertEquals(parent, appointment.getParent());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(child, appointment.getChild());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(timeSlot, appointment.getTimeSlot());
        assertEquals("First checkup", appointment.getNote());
        assertEquals(AppointmentStatus.SCHEDULED, appointment.getStatus());
        assertNotNull(appointment.getCreatedAt());
        assertNotNull(appointment.getUpdatedAt());
    }

    @Test
    void testCreateAppointmentWithNulls() {
        Appointment appointment = AppointmentFactory.createAppointment(
                null, null, null, null, null, null
        );
        assertNotNull(appointment); // factory still builds, just with nulls
        assertNull(appointment.getParent());
        assertNull(appointment.getDoctor());
        assertNull(appointment.getChild());
    }




}
