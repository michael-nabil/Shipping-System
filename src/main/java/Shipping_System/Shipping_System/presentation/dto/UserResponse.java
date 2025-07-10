package Shipping_System.Shipping_System.presentation.dto;

public class UserResponse {
    private String email;
    private String role;

    public UserResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }

    // Getters
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
