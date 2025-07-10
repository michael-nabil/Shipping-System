package Shipping_System.Shipping_System.presentation.dto;

import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.domin.enitiy.Role;



public class RegisterRequest {
   // @NotBlank(message = "Email is required")
    private String email;

   // @NotBlank(message = "Password is required")
    private String password;

    private Role role;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}