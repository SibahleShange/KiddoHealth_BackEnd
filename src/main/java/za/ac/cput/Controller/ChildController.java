package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.Domain.Child;
import za.ac.cput.Service.ChildService;
import za.ac.cput.Service.FileStorageService;
import java.util.List;

@RestController
@RequestMapping("/child")
@CrossOrigin(origins = "http://localhost:3000")
public class ChildController {

    private ChildService childService;
    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/create")
    public Child create(@RequestBody Child child){
        return childService.create(child);
    }
    // NEW ENDPOINT: Get all children for a specific parent
    // This is what the dashboard will call after login if the login response doesn't include children.
    @GetMapping("/by-parent/{parentId}")
    public ResponseEntity<List<Child>> getChildrenByParent(@PathVariable long parentId) {
        List<Child> children = childService.getChildrenByParent(parentId);
        if (children == null || children.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(children);
    }
    // NEW ENDPOINT: Upload a profile picture for a specific child
    @PostMapping("/{childId}/upload-picture")
    public ResponseEntity<Child> uploadProfilePicture(@PathVariable long childId, @RequestParam("file") MultipartFile file) {
        Child child = childService.read(childId);
        if (child == null) {
            return ResponseEntity.notFound().build();
        }

        String fileUrl = fileStorageService.storeFile(file);

        // Update the child entity with the new picture URL using the builder
        Child updatedChild = new Child.Builder()
                .copy(child)
                .setProfilePictureUrl(fileUrl)
                .build();

        childService.update(updatedChild);

        return ResponseEntity.ok(updatedChild);
    }


        @GetMapping("/read/{childId}")
    public Child read(@PathVariable long childId){
     return childService.read(childId);
    }

    @PutMapping("/update")
    public Child update(@RequestBody Child child){
     return childService.update(child);
    }

    @DeleteMapping("/Delete/{childId}")
    public void delete(@PathVariable long childId){
     childService.delete(childId);
    }
    @GetMapping("/getAll")
    public List<Child> getAll(){
     return childService.getAll();
    }

//    @GetMapping("/byParent/{parentId}")
//    public List<Child> getChildrenByParent(@PathVariable long parentId) {
//        return childService.getChildrenByParent(parentId);
//    }

}
