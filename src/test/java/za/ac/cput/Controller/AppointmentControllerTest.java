package za.ac.cput.Controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.Domain.*;
import za.ac.cput.Factory.AppointmentFactory;
import za.ac.cput.Factory.ChildFactory;
import za.ac.cput.Factory.DoctorFactory;
import za.ac.cput.Factory.ParentFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AppointmentControllerTest {


    private static Parent parent;
    private static Doctor doctor;
    private static Child child;
    private static Appointment appointment;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/appointments";

    @BeforeAll
    static void setUp() {
        parent = ParentFactory.createParent(
                "John", "Doe", "john.doe@example.com",
                "0831234567", "johnParent", "Password123!",
                "123 Main St", User.Role.PATIENT
        );

        doctor = DoctorFactory.createDoctor(
                "John",                   // name
                "Smith",                  // surname
                "john.smith@example.com", // email
                "0821234567",             // phone number
                "johnDoctor",             // username
                "Password123!",           // password
                "Pediatrics",             // specialization
                "DOC123456",              // license number
                User.Role.DOCTOR          // role
        );

        child = ChildFactory.createChild(
                "2002025067085", "Baby", "Doe", "Female", parent, null
        );

        appointment = AppointmentFactory.createAppointment(
                parent, doctor, child, LocalDate.of(2025, 10, 10),
                LocalTime.of(10, 30), "First checkup"
        );
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Appointment> response = restTemplate.postForEntity(url, appointment, Appointment.class);

        assertNotNull(response.getBody());
        Appointment saved = response.getBody();
        assertEquals(appointment.getNote(), saved.getNote());
        appointment = saved; // update static variable for further tests

        System.out.println("Created appointment: " + saved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + appointment.getAppointmentId();
        ResponseEntity<Appointment> response = restTemplate.getForEntity(url, Appointment.class);

        assertNotNull(response.getBody());
        assertEquals(appointment.getAppointmentId(), response.getBody().getAppointmentId());
        System.out.println("Read appointment: " + response.getBody());
    }

    @Test
    void c_update() {
        Appointment updated = new Appointment.Builder().copy(appointment)
                .setNote("Updated checkup note")
                .build();

        restTemplate.put(BASE_URL + "/update", updated);

        ResponseEntity<Appointment> response = restTemplate.getForEntity(
                BASE_URL + "/read/" + appointment.getAppointmentId(), Appointment.class
        );

        assertNotNull(response.getBody());
        assertEquals("Updated checkup note", response.getBody().getNote());
        appointment = response.getBody();

        System.out.println("Updated appointment: " + appointment);
    }

    @Test
    void d_getAll() {
        ResponseEntity<Appointment[]> response = restTemplate.getForEntity(BASE_URL + "/getAll", Appointment[].class);

        assertNotNull(response.getBody());
        List<Appointment> list = List.of(response.getBody());
        assertTrue(list.size() > 0);

        System.out.println("All appointments:");
        list.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        restTemplate.delete(BASE_URL + "/delete/" + appointment.getAppointmentId());

        ResponseEntity<Appointment> response = restTemplate.getForEntity(
                BASE_URL + "/read/" + appointment.getAppointmentId(), Appointment.class
        );

        assertNull(response.getBody());
        System.out.println("Appointment deleted successfully");
    }

}
