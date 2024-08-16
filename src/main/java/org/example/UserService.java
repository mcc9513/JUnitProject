package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    // A simple map to simulate a database
    private Map<String, User> userDatabase = new HashMap<>();

    public boolean registerUser(User user) {
        if (user == null || userDatabase.containsKey(user.getUsername())) {
            return false; // User already exists or user is null
        }

        userDatabase.put(user.getUsername(), user);
        return true; // User registered successfully
    }

    public User loginUser(String username, String password) {
        if (username == null || password == null) {
            return null; // Null inputs
        }

        User user = userDatabase.get(username);

        if (user == null || !user.getPassword().equals(password)) {
            return null; // User not found or wrong password
        }

        return user; // Login successful
    }

    public boolean updateUserProfile(User user, String newUsername, String newPassword, String newEmail) {
        if (user == null || newUsername == null || newPassword == null || newEmail == null) {
            return false; // Invalid input
        }

        if (userDatabase.containsKey(newUsername)) {
            return false; // New username is already taken
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);

        userDatabase.put(newUsername, user);
        return true; // User profile updated successfully
    }
}
