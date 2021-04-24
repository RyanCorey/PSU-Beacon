package repositories;

import com.google.inject.ImplementedBy;
import entities.User;
import entities.enumerations.Role;
import services.UserService;

import java.util.List;

@ImplementedBy(UserService.class)
public interface UserRepository {
    List<User> list();
    List<User> findAllByRole(Role role);
    User getUserById(Long id);
    User save(User user);
    User update(User user);
    void delete(Long id);
}
