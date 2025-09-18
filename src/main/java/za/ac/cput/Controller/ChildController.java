package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Child;
import za.ac.cput.Service.ChildService;

import java.util.List;

@RestController
@RequestMapping("/child")
@CrossOrigin(origins = "http://localhost:3000")
public class ChildController {
    private ChildService childService;

 @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/create")
    public Child create(@RequestBody Child child){
        return childService.create(child);
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

    @GetMapping("/byParent/{parentId}")
    public List<Child> getChildrenByParent(@PathVariable long parentId) {
        return childService.getChildrenByParent(parentId);
    }

}
