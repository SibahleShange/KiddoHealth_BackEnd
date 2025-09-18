package za.ac.cput.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Domain.Child;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Domain.User;
import za.ac.cput.Factory.ChildFactory;
import za.ac.cput.Factory.MedicalRecordFactory;
import za.ac.cput.Factory.ParentFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

class ChildServiceTest {
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private ChildService childService;

    private static MedicalRecord medicalRecord;
    private static Parent parent;
    private static Child child;

    @BeforeAll
    static void setUp() {
        medicalRecord = MedicalRecordFactory.createMedicalRecord("Follow-up", LocalDate.of(2025, 7, 27), "Patient is responding well to treatment.");
         parent = ParentFactory.createParent("Thabo", "Mthembu", "thabo.mthembu@example.com", "0821234567", "thaboParent",
                "Strong@123", "123 Mandela Street, Soweto", User.Role.PATIENT);
    }
   // private static Child child = ChildFactory.createChild("2001015062087","Sipho","Mthembu",parent,medicalRecord);



    @Test
    void a_create() {
        MedicalRecord createMedicalRecord = medicalRecordService.create(medicalRecord);
        assertNotNull(createMedicalRecord);
        System.out.println("Created: " + createMedicalRecord);

        Parent createParent = parentService.create(parent);
        assertNotNull(createParent);
        System.out.println("Created: " + createParent);

        child = ChildFactory.createChild("2210150620881","Zama","Mthembu","Femal",createParent,createMedicalRecord);
        assertNotNull(child);

        Child createdChild = childService.create(child);
        assertNotNull(createdChild);
        System.out.println("Created: " + createdChild);

    }

    @Test
    void b_read() {
        Child read = childService.read(child.getChildId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Child newChild = new Child.Builder().copy(child).setSurname("Sibahle").build();
        Child updatedChild = childService.update(newChild);
        assertNotNull(updatedChild);
        System.out.println("Updated: " + updatedChild);
    }

//    @Test
//    void e_delete() {
//        childService.delete(child.getChildId());
//        assertNull(childService.read(child.getChildId()));
//        System.out.println("Deleted: " + child.getName());
//    }

    @Test
    void d_getAll() {
        childService.getAll();
        for(Child child : childService.getAll()) {
            System.out.println("get all " + child);
        }
    }
}