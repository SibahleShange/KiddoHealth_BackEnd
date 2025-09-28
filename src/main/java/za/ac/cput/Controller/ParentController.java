package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Domain.Child;
import za.ac.cput.Service.ParentService;
import za.ac.cput.Service.ChildService;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/parent")
@CrossOrigin(origins = "http://localhost:3000")
public class ParentController {

    private ParentService parentService;
    private ChildService childService;

    @Autowired
    public ParentController(ParentService parentService, ChildService childService) {
        this.parentService = parentService;
        this.childService = childService;
    }

    @PostMapping("/create")
    public Parent create(@RequestBody Parent parent) {
        return parentService.create(parent);
    }

    @GetMapping("/read/{userId}")
    public Parent read(@PathVariable Long userId){
        return parentService.read(userId);
    }

    @PutMapping("/update")
    public Parent update(@RequestBody Parent parent) {
        return parentService.update(parent);
    }

    @DeleteMapping("/delete/{userId}")
    public Parent delete(@PathVariable Long userId){
        parentService.delete(userId);
        return null;
    }

    @GetMapping("/getAll")
    public List<Parent> getAll(){
        return parentService.getAll();
    }

    //  Modified login to also fetch children
    @PostMapping("/login")
    public Parent login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String role = loginRequest.get("role");

        Parent parent = parentService.findByEmail(email);
        if (parent == null) {
            System.out.println("Parent not found for email: " + email);
            throw new RuntimeException("Invalid credentials");
        }

        // If passwords are stored plain (your current setup)
        if (!parent.getPassword().equals(password) || !parent.getRole().toString().equalsIgnoreCase(role))  {
            System.out.println("Password mismatch for email: " + email);
            throw new RuntimeException("Invalid credentials");
        }

        //  fetch and attach children
        List<Child> children = childService.getChildrenByParent(parent.getUserId());
        parent.setChildren(children);

        // Optional: remove password before returning
        parent.setPassword(null);

        System.out.println("Login successful for email: " + email + ", role=" + parent.getRole());
        return parent;
    }

    // Get parent by email
    @GetMapping("/byEmail/{email}")
    public Parent getByEmail(@PathVariable String email) {
        Parent parent = parentService.findByEmail(email.trim());
        if (parent == null) {
            throw new RuntimeException("Parent not found");
        }
        parent.setPassword(null); // optional, don’t send password back
        return parent;
    }

    // Update parent password
    @PutMapping("/updatePassword/{userId}")
    public Parent updatePassword(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        return parentService.updatePassword(userId, newPassword);
    }
}
