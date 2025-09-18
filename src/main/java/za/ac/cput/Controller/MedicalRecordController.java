package za.ac.cput.Controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Service.MedicalRecordService;

import java.util.List;

@RestController
@RequestMapping("/medical")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalRecordController {
    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/create")
    public MedicalRecord create(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.create(medicalRecord);
    }
    @GetMapping("/read/{medicalRecordID}")
    public MedicalRecord read(@PathVariable Long medicalRecordID) {
        return medicalRecordService.read(medicalRecordID);
    }

    @PutMapping("/update")
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.update(medicalRecord);
    }

//    @DeleteMapping("/delete/{medicalRecordID}")
//    public void delete(@PathVariable Long medicalRecordID) {
//        return

    @GetMapping("/getAll")
    public List<MedicalRecord> getAll(){
        return medicalRecordService.getAll();
    }
}
