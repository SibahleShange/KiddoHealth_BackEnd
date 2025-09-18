package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Child;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByParent_UserId(long parentId);

}
