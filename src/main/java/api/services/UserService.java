package api.services;

import api.models.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);

}
