package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.MedicalRecord;
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
