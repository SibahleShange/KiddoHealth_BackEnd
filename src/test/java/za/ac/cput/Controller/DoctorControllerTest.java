package za.ac.cput.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.User;
import za.ac.cput.Factory.DoctorFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)

class DoctorControllerTest {
    public static Doctor doctor;
    @Autowired
    private TestRestTemplate restTemplate ;
    private static final String BASE_URL = "http://localhost:8080/kiddohealth/doctor";

    @BeforeAll
    static void setUp() {
        doctor = DoctorFactory.createDoctor(
                "Dr Thabo",
                "Mokoena",
                "thabo.mokoena@clinic.co.za",
                "0831234567",
                "drthabo",
                "HeartCare@2025",
                "Pediatrician",
                "DR654321",
                User.Role.DOCTOR
        );

    }

    @Test
    void a_create() {
        String Url = BASE_URL + "/create";
        ResponseEntity<Doctor> response = restTemplate.postForEntity(Url, doctor, Doctor.class);
        assertNotNull(response);
        System.out.println(response.getBody());
        Doctor saved = response.getBody();
        assertNotNull(saved);
        assertEquals(doctor.getLicenseNumber(), saved.getLicenseNumber());

        doctor = saved;
        System.out.println(saved.toString());

    }

    @Test
    void b_read() {
        System.out.println("Doctor ID for read: " + doctor.getUserId());
        System.out.println(doctor.toString());
        String Url = BASE_URL + "/read/" + doctor.getUserId();
     ResponseEntity<Doctor> response = restTemplate.getForEntity(Url, Doctor.class);
     assertEquals(doctor.getUserId(), response.getBody().getUserId());
     Doctor read = response.getBody();
        System.out.println(read.toString());


    }

    @Test
    void c_update() {
        Doctor update = new Doctor.Builder().copy(doctor).setName("Dr Mashange").build();
        String Url = BASE_URL + "/update" ;
        this.restTemplate.put(Url,update);

        ResponseEntity<Doctor> response = this.restTemplate.getForEntity(BASE_URL + "/read/" + doctor.getUserId(), Doctor.class);
        assertNotNull(response.getBody());
        Doctor updatedDoctor = response.getBody();
        System.out.println(updatedDoctor.toString());
    }

    @Test
    void d_getAll() {
        String Url = BASE_URL + "/getAll";
        ResponseEntity<Doctor[]> response = restTemplate.getForEntity(Url, Doctor[].class);
        assertNotNull(response.getBody());
        System.out.println("Get All");
        for (Doctor doctor : response.getBody()) {
            System.out.println(doctor.toString());
        }
    }
}