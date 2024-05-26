package id.ac.ui.cs.advprog.userfunctionality.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;

    @Value("${AUTH_API}")
    private String authServiceUrl;

    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean verifyUser(String authorizationHeader) {
        return verify(authorizationHeader, "/api/test/user");
    }

    @Override
    public boolean verifyAdmin(String authorizationHeader) {
        return verify(authorizationHeader, "/api/test/admin");
    }

    private boolean verify(String token, String endpoint) {
        if (token == null) {
            return false;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            String url = authServiceUrl + endpoint;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
}