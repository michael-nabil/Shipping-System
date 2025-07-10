package Shipping_System.Shipping_System.data.model;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import jakarta.persistence.*;
import lombok.Data;

    @Entity
    @Table(name = "users")
    @Data
    public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;

        public Long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public Role getRole() {
            return role;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public void addAttribute(String error, String invalidEmailOrPassword) {
        }
    }
