package id.ac.ui.cs.advprog.userfunctionality.service;

public interface AuthService {
    public boolean verifyUser(String authorizationHeader);
    public boolean verifyAdmin(String authorizationHeader);
}
