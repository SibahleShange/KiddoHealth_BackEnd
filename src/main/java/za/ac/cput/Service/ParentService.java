package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Repository.ParentRepository;

import java.util.List;
@Service
public class ParentService implements IParentService{

private ParentRepository parentRepository;

@Autowired
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent create(Parent parent) {
        return this.parentRepository.save(parent);
    }

    @Override
    public Parent read(Long id) {
        return this.parentRepository.findById(id).orElse(null);
    }

    @Override
    public Parent update(Parent parent) {
        return this.parentRepository.save(parent);
    }

    @Override
    public void delete(Long id) {
    this.parentRepository.deleteById(id);

    }

    @Override
    public Parent findByEmail(String email) {
        return parentRepository.findByEmailIgnoreCase(email)
                .orElse(null);

    }

    @Override
    public Parent updatePassword(Long userId, String newPassword) {
        // Fetch parent directly from repository
        Parent parent = parentRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        // Update password
        parent.setPassword(newPassword);

        // Save changes directly
        return parentRepository.save(parent);
    }

    @Override
    public List<Parent> getAll() {
        return this.parentRepository.findAll();
    }
}
