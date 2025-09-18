package za.ac.cput.Service;

import za.ac.cput.Domain.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService extends IService <MedicalRecord,Long>{
    List<MedicalRecord> getAll();
}
