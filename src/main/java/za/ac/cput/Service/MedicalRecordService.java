package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Repository.MedicalRecordRepository;

import java.util.List;
@Service
public class MedicalRecordService implements IMedicalRecordService {
  private MedicalRecordRepository medicalRecordRepository;

  @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalRecord) {
        return this.medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord read(Long id) {
        return this.medicalRecordRepository.findById(id).orElse(null);
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        return this.medicalRecordRepository.save(medicalRecord);
    }
    @Override
    public List<MedicalRecord> getAll() {
        return this.medicalRecordRepository.findAll();
    }
}
