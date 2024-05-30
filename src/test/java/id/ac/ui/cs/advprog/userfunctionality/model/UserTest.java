package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    UserEntity user;

    @BeforeEach
    void testUserModel() {
        user = new UserEntity();

        user.setUsername("John");
        user.setPassword("password123");

    }


    @Test
    void testGetUsername() {
        assertEquals("John", user.getUsername());
    }


    @Test
    void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }
}
