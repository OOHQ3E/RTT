package org.example.User;

import java.util.List;

public interface UserService {
    User getUserById(int userId);
    void updateUser(User user);
    List<User> getAllUsers();
}
