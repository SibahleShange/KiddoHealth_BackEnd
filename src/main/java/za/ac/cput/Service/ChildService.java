package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Child;
import za.ac.cput.Repository.ChildRepository;

import java.util.List;
@Service
public class ChildService implements IChildService {

    private ChildRepository childRepository;

@Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public Child create(Child child) {
        return this.childRepository.save(child);
    }

    @Override
    public Child read(Long id) {
        return this.childRepository.findById(id).orElse(null);
    }

    @Override
    public Child update(Child child) {
        return this.childRepository.save(child);
    }

    @Override
    public void delete(Long id) {
 this.childRepository.deleteById(id);
    }

    @Override
    public List<Child> getChildrenByParent(long parentId) {
        return childRepository.findByParent_UserId(parentId);
    }



    @Override
    public List<Child> getAll() {
        return this.childRepository.findAll();
    }

}
