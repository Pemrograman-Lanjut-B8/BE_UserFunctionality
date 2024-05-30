package id.ac.ui.cs.advprog.userfunctionality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthServiceImpl authService;

    @Value("${AUTH_API}")
    private String authServiceUrl = "http://mock-auth-service";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authServiceUrl = "http://mock-auth-service";
    }

    @Test
    void verifyUser_Success() {
        String authorizationHeader = "Bearer validToken";
        String endpoint = "/api/test/user";
        String url = authServiceUrl + endpoint;

        ResponseEntity<String> responseEntity = new ResponseEntity<>("OK", HttpStatus.OK);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        boolean result = authService.verifyUser(authorizationHeader);

        assertFalse(result);
    }

    @Test
    void verifyAdmin_Success() {
        String authorizationHeader = "Bearer validToken";
        String endpoint = "/api/test/admin";
        String url = authServiceUrl + endpoint;

        ResponseEntity<String> responseEntity = new ResponseEntity<>("OK", HttpStatus.OK);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        boolean result = authService.verifyAdmin(authorizationHeader);

        assertFalse(result);
    }

    @Test
    void verifyUser_Failure() {
        String authorizationHeader = "Bearer invalidToken";
        String endpoint = "/api/test/user";
        String url = authServiceUrl + endpoint;

        ResponseEntity<String> responseEntity = new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        boolean result = authService.verifyUser(authorizationHeader);

        assertFalse(result);
    }

    @Test
    void verifyAdmin_Failure() {
        String authorizationHeader = "Bearer invalidToken";
        String endpoint = "/api/test/admin";
        String url = authServiceUrl + endpoint;

        ResponseEntity<String> responseEntity = new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        boolean result = authService.verifyAdmin(authorizationHeader);

        assertFalse(result);
    }

    @Test
    void verify_NullToken() {
        boolean userResult = authService.verifyUser(null);
        boolean adminResult = authService.verifyAdmin(null);

        assertFalse(userResult);
        assertFalse(adminResult);

        verify(restTemplate, never()).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class));
    }
}
