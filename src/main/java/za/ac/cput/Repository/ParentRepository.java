package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Parent;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findByEmail(String email);
//Parent updatePassword(Long userId, String newPassword);
    Optional<Parent> findByEmailIgnoreCase(String email);
//Optional<Parent> findByEmailIgnoreCase(String email);
}
