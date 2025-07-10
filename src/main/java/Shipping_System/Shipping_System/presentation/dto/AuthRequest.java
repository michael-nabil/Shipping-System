package Shipping_System.Shipping_System.presentation.dto;




public class AuthRequest {
//
//   @NotBlank(message = "Email is required")
//  @Email(message = "Invalid email format")
    private String email;

   //@NotBlank(message = "Password is required")
    private String password;

    // getters and setters (or use Lombok @Data/@Getter/@Setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
