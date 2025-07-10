package Shipping_System.Shipping_System.presentation.controller;

import Shipping_System.Shipping_System.domin.enitiy.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.data.servicesImp.AuthServiceImpl;
import org.springframework.web.bind.annotation.*;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import Shipping_System.Shipping_System.presentation.dto.*;
import Shipping_System.Shipping_System.presentation.exception.AuthException;

@Controller
@RequestMapping("/auth")
public class AuthController {
        private final AuthServiceImpl authService;

        public AuthController(AuthServiceImpl authService) {
            this.authService = authService;
        }

        @GetMapping("/register")
        public String showRegisterPage() {
            return "register";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute RegisterRequest request, Model model) {
            try {
                UserModel user = new UserModel();
                user.setEmail(request.getEmail());
                user.setPassword(request.getPassword());
                user.setRole(request.getRole());

                authService.register(user);
                return "redirect:/auth/login?success";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "register";
            }
        }

        @GetMapping("/login")
        public String showLoginPage(@RequestParam(required = false) String error,
                                    @RequestParam(required = false) String success,
                                    Model model) {
            if (error != null) model.addAttribute("error", error);
            if (success != null) model.addAttribute("success", success);
            return "login";
        }

//        @PostMapping("/login")
//        public String login(@RequestParam String email,
//                            @RequestParam String password,
//                            Model model) {
//            try {
//                Role role = authService.login(email, password);
//                if (role == Role.DELIVERY_PERSON) {
//                    return "redirect:/delivery/dashboard?email=" + email;
//                } else if (role == Role.MERCHANT) {
//                    return "redirect:/merchant/dashboard?email=" + email;
//                } else {
//                    return "redirect:/login";
//                }
//            } catch (AuthException e) {
//                model.addAttribute("error", e.getMessage());
//                return "login";
//            }
//        }
@PostMapping("/login")
public String login(@RequestParam String email,
                    @RequestParam String password,
                    Model model,
                    HttpSession session) {
    try {
        UserModel user = authService.login(email, password);

        // ✅ Store user ID, email, and role in session
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getRole());

        if (user.getRole() == Role.DELIVERY_PERSON) {
            return  "redirect:/delivery/dashboard?email=" + email;
        } else if (user.getRole() == Role.MERCHANT) {
            return "redirect:/merchant/dashboard?email=" + email;
        } else {
            return  "redirect:/login";
        }
    } catch (AuthException e) {
        model.addAttribute("error", e.getMessage());
        return "login";
    }
}


//            try {
//                Role role = authService.login(email, password);
//                return "redirect:/" + role.toString().toLowerCase() + "_dashboard?email=" + email;
//            } catch (AuthException e) {
//                model.addAttribute("error", e.getMessage());
//                return "login";
//            }
//        }

        @GetMapping("/logout")
        public String logout() {
            return "redirect:/auth/login";
        }
    }