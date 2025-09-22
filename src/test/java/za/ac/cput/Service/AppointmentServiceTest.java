package za.ac.cput.Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Domain.Child;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest

public class AppointmentServiceTest {


    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentService service;

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Parent parent = new Parent.Builder()
                .setUserId(1L)
                .setName("John Doe")
                .build();

        Doctor doctor = new Doctor.Builder()
                .setUserId(1L)
                .setName("Dr. Smith")
                .build();

        Child child = new Child.Builder()
                .setChildId(1L)
                .setName("Baby Doe")
                .build();

        appointment = new Appointment.Builder()
                .setAppointmentId(1L)
                .setParent(parent)
                .setDoctor(doctor)
                .setChild(child)
                .setAppointmentDate(LocalDate.of(2025, 10, 10))
                .setTimeSlot(LocalTime.of(10, 30))
                .setNote("First checkup")
                .build();
    }

    @Test
    void testCreateAppointment() {
        when(repository.save(appointment)).thenReturn(appointment);

        Appointment created = service.createAppointment(appointment);

        assertNotNull(created);
        assertEquals(appointment.getAppointmentId(), created.getAppointmentId());

    }

    @Test
    void testGetAppointmentById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(appointment));

        Appointment found = service.getAppointmentById(1L);

        assertNotNull(found);
        assertEquals("First checkup", found.getNote());

    }

    @Test
    void testGetAppointmentById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Appointment found = service.getAppointmentById(2L);

        assertNull(found);

    }

    @Test
    void testUpdateAppointment() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(appointment)).thenReturn(appointment);

        Appointment updated = service.updateAppointment(appointment);

        assertNotNull(updated);
        assertEquals(appointment.getNote(), updated.getNote());

    }

    @Test
    void testDeleteAppointment_Success() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean deleted = service.deleteAppointment(1L);

        assertTrue(deleted);

    }

    @Test
    void testDeleteAppointment_Failure() {
        when(repository.existsById(99L)).thenReturn(false);

        boolean deleted = service.deleteAppointment(99L);

        assertFalse(deleted);

    }

    @Test
    void testGetAppointmentsByDoctorAndDate() {
        when(repository.findByDoctorIdAndAppointmentDate(1L, LocalDate.of(2025, 10, 10)))
                .thenReturn(List.of(appointment));

        List<Appointment> results =
                service.getAppointmentsByDoctorAndDate(1L, LocalDate.of(2025, 10, 10));

        assertEquals(1, results.size());
        assertEquals("First checkup", results.get(0).getNote());

    }


}
