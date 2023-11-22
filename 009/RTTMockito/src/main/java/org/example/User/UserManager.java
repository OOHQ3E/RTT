package org.example.User;

import java.util.List;

public class UserManager {
    private UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }
    public String getUsernameById(int userId){
        User user = userService.getUserById(userId);
        return user != null ? user.getUsername() : null;
    }
    public void updateUserUsername(int userId, String newUsername){
        User user = userService.getUserById(userId);
        if (user != null){
            user.setUsername(newUsername);
            userService.updateUser(user);
        }
    }
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
