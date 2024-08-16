
package org.example;

import org.example.User;
import org.example.UserService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    private User user;

    @Before
    public void setUp() {
        userService = new UserService();
        user = new User("testuser", "password", "test@example.com");

        // Ensure initial state is clear
        userService.registerUser(user);
    }

    // Test for registerUser method
    @Test
    public void testRegisterUser_Success() {
        User newUser = new User("newuser", "newpassword", "new@example.com");
        assertTrue(userService.registerUser(newUser));
    }

    @Test
    public void testRegisterUser_AlreadyExists() {
        assertFalse(userService.registerUser(user));
    }

    @Test
    public void testRegisterUser_NullUser() {
        assertFalse(userService.registerUser(null));
    }

    // Test for loginUser method
    @Test
    public void testLoginUser_Success() {
        User loggedInUser = userService.loginUser("testuser", "password");
        assertNotNull(loggedInUser);
    }

    @Test
    public void testLoginUser_UserNotFound() {
        User loggedInUser = userService.loginUser("nonexistent", "password");
        assertNull(loggedInUser);
    }

    @Test
    public void testLoginUser_WrongPassword() {
        User loggedInUser = userService.loginUser("testuser", "wrongpassword");
        assertNull(loggedInUser);
    }

    @Test
    public void testLoginUser_NullUsername() {
        User loggedInUser = userService.loginUser(null, "password");
        assertNull(loggedInUser);
    }

    @Test
    public void testLoginUser_NullPassword() {
        User loggedInUser = userService.loginUser("testuser", null);
        assertNull(loggedInUser);
    }

    // Test for updateUserProfile method
    @Test
    public void testUpdateUserProfile_Success() {
        assertTrue(userService.updateUserProfile(user, "newuser", "newpassword", "new@example.com"));
    }

    @Test
    public void testUpdateUserProfile_UsernameTaken() {
        User anotherUser = new User("newuser", "password", "new@example.com");
        userService.registerUser(anotherUser);

        assertFalse(userService.updateUserProfile(user, "newuser", "newpassword", "new@example.com"));
    }

    @Test
    public void testUpdateUserProfile_NullUser() {
        assertFalse(userService.updateUserProfile(null, "newuser", "newpassword", "new@example.com"));
    }

    @Test
    public void testUpdateUserProfile_NullUsername() {
        assertFalse(userService.updateUserProfile(user, null, "newpassword", "new@example.com"));
    }

    @Test
    public void testUpdateUserProfile_NullPassword() {
        assertFalse(userService.updateUserProfile(user, "newuser", null, "new@example.com"));
    }

    @Test
    public void testUpdateUserProfile_NullEmail() {
        assertFalse(userService.updateUserProfile(user, "newuser", "newpassword", null));
    }
}
