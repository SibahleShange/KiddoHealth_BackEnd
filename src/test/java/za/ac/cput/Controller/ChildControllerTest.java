package za.ac.cput.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.Domain.Child;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Domain.User;
import za.ac.cput.Factory.ChildFactory;
import za.ac.cput.Factory.MedicalRecordFactory;
import za.ac.cput.Factory.ParentFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)

class ChildControllerTest {
private static Parent parent;
private static MedicalRecord medicalRecord;
private static Child child;

@Autowired
private TestRestTemplate restTemplate ;
private static final String BASE_URL = "http://localhost:8080/kiddohealth/child";

    @BeforeAll
    static void setUp() {
        medicalRecord = MedicalRecordFactory.createMedicalRecord(
                "Initial Consultation",
                LocalDate.of(2025, 8, 5),
                "Child shows signs of improvement and requires further observation."
        );

        parent = ParentFactory.createParent(
                "Nomsa",
                "Dlamini",
                "nomsa.dlamini@example.com",
                "0839876543",
                "nomsaParent",
                "SecurePass!2025",
                "45 Freedom Drive, Umlazi",
                User.Role.PATIENT
        );

        child = ChildFactory.createChild(
                "2002025067085",
                "Sipho",
                "Dlamini",
                "Female",
                parent,
                medicalRecord
        );

    }

    @Test
    void a_create() {
        System.out.println(child.toString());
        assertNotNull(child.getChildId(), "Child id should not be  null");

        String Url = BASE_URL + "/create";
        ResponseEntity<Child> response = restTemplate.postForEntity(Url, child, Child.class);
        assertNotNull(response);
        System.out.println(response.getBody());
        
        Child savedChild = response.getBody();
        assertNotNull(savedChild);
        assertEquals(child.getIdentityNumber(), savedChild.getIdentityNumber());

        child = savedChild;

        System.out.println(savedChild.toString());

    }

    @Test
    void b_read() {
        String Url = BASE_URL + "/read/" + child.getChildId();
        ResponseEntity<Child> response = restTemplate.getForEntity(Url, Child.class);
        assertNotNull(response.getBody());
        assertEquals(child.getChildId(), response.getBody().getChildId());
        Child readChild = response.getBody();
        System.out.println(readChild.toString());
    }

    @Test
    void c_update() {
        Child updatedChild = new Child.Builder().copy(child).setName("sbahle").build();
        String Url = BASE_URL + "/update";
        this.restTemplate.put(Url, updatedChild);
        ResponseEntity<Child> response = restTemplate.getForEntity(BASE_URL +"/read/" + child.getChildId(), Child.class);
        assertNotNull(response.getBody());
        Child readChild = response.getBody();
        System.out.println(readChild.toString());
    }

//    @Test
//    void d_delete() {
//        String Url = BASE_URL + "/delete/" + child.getChildId();
//        this.restTemplate.delete(Url);
//        ResponseEntity<Child> response = restTemplate.getForEntity(BASE_URL +"/read/" + child.getChildId(), Child.class);
//        Child readChild = response.getBody();
//        System.out.println(readChild.toString());
//    }

    @Test
    void getAll() {
        String Url = BASE_URL + "/getAll";
        ResponseEntity<Child[]> response = restTemplate.getForEntity(Url, Child[].class);
        assertNotNull(response.getBody());
        System.out.println("Get all child records successful");
        for (Child child : response.getBody()) {
            System.out.println(child.toString());
        }
    }
}