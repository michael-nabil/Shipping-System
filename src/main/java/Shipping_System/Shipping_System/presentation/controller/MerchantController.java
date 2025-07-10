package Shipping_System.Shipping_System.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "merchant-dashboard";
    }
}



