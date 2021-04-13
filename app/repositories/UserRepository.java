package repositories;

import com.google.inject.ImplementedBy;
import entities.User;
import services.UserService;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(UserService.class)
public interface UserRepository {
    CompletionStage<Stream<User>> list();

    CompletionStage<User> getUserById(Long id);

    CompletionStage<User> save(User user);

    CompletionStage<User> update(User user);
}
