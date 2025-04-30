package api.services.impl;

import api.models.User;
import api.repositories.UserRepository;
import api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsById(userToCreate.getId()))
            throw new IllegalArgumentException("This user ID already exists");
        else if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber()))
            throw new IllegalArgumentException("This account number ID already exists to other user");
        return userRepository.save(userToCreate);
    }
}
