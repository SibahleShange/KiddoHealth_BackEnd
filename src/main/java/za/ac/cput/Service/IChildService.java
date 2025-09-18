package za.ac.cput.Service;

import za.ac.cput.Domain.Child;

import java.util.List;

public interface IChildService extends IService<Child, Long> {
    List<Child> getAll();
    void delete(Long id);
    List<Child> getChildrenByParent(long parentId);

}
