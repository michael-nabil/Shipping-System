package Shipping_System.Shipping_System.presentation.dto;
public class AuthResponse {
    private String message;
    private String role;

    public AuthResponse(String message) {
        this.message = message;

    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    // Setters (optional, if you need them)
    public void setMessage(String message) {
        this.message = message;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
