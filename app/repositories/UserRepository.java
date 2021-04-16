package repositories;

import com.google.inject.ImplementedBy;
import entities.User;
import services.UserService;

import java.util.List;

@ImplementedBy(UserService.class)
public interface UserRepository {
    List<User> list();
    User getUserById(Long id);
    User save(User user);
    User update(User user);
}
