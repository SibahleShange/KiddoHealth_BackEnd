package za.ac.cput.Service;

import za.ac.cput.Domain.Parent;

import java.util.List;

public interface IParentService extends IService<Parent,Long>{
    List<Parent> getAll();
    void delete(Long id);
    Parent findByEmail(String email);
    Parent updatePassword(Long userId, String newPassword);

}
