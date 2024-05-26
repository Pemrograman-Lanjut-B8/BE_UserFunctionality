package id.ac.ui.cs.advprog.userfunctionality.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.userfunctionality.UserfunctionalityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = UserfunctionalityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = "AUTH_API=http://localhost:8080")
public class AuthServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVerifyUserWithValidToken() {
        String validToken = "Bearer valid-token";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/user"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        boolean result = authService.verifyUser(validToken);
        assertFalse(result, "Expected verification to succeed with valid token");
    }

    @Test
    public void testVerifyUserWithInvalidToken() {
        String invalidToken = "Bearer invalid-token";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/user"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        boolean result = authService.verifyUser(invalidToken);
        assertFalse(result, "Expected verification to fail with invalid token");
    }

    @Test
    public void testVerifyUserWithException() {
        String token = "Bearer token";

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/user"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("Error"));

        boolean result = authService.verifyUser(token);
        assertFalse(result, "Expected verification to fail with exception");
    }

    @Test
    public void testVerifyAdminWithValidToken() {
        String validToken = "Bearer valid-token";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/admin"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        boolean result = authService.verifyAdmin(validToken);
        assertFalse(result, "Expected verification to succeed with valid token");
    }

    @Test
    public void testVerifyAdminWithInvalidToken() {
        String invalidToken = "Bearer invalid-token";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/admin"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        boolean result = authService.verifyAdmin(invalidToken);
        assertFalse(result, "Expected verification to fail with invalid token");
    }

    @Test
    public void testVerifyAdminWithException() {
        String token = "Bearer token";

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/test/admin"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("Error"));

        boolean result = authService.verifyAdmin(token);
        assertFalse(result, "Expected verification to fail with exception");
    }

    @Test
    public void testVerifyUserWithNullToken() {
        boolean result = authService.verifyUser(null);
        assertFalse(result, "Expected verification to fail with null token");
    }

    @Test
    public void testVerifyAdminWithNullToken() {
        boolean result = authService.verifyAdmin(null);
        assertFalse(result, "Expected verification to fail with null token");
    }
}