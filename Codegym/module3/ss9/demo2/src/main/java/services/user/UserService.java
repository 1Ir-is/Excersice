package services.user;

import models.User;
import repositories.user.IUserRepository;
import repositories.user.UserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean register(User user) {
        if (isEmailExists(user.getEmail())) {
            return false;
        }
        return userRepository.save(user);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
