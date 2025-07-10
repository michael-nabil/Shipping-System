package Shipping_System.Shipping_System.presentation.controller;

import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import Shipping_System.Shipping_System.domin.enitiy.User;
import Shipping_System.Shipping_System.domin.service.AuthService;
import Shipping_System.Shipping_System.presentation.dto.AssignDeliveryRequest;
import Shipping_System.Shipping_System.presentation.dto.DeliveryAssignResponse;
import Shipping_System.Shipping_System.domin.service.DeliveryAssignService;
import Shipping_System.Shipping_System.presentation.dto.PackageResponse;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;    import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static Shipping_System.Shipping_System.domin.enitiy.Role.DELIVERY_PERSON;


@Controller
@RequestMapping("/delivery")
public class DeliveryAssignController {

    private final DeliveryAssignService deliveryAssignService;
private final AuthService authService;
    public DeliveryAssignController(DeliveryAssignService deliveryAssignService, AuthService authService) {
        this.deliveryAssignService = deliveryAssignService;
        this.authService = authService;
    }

//    @PostMapping("/assign")
//    public String assignDelivery(@ModelAttribute AssignDeliveryRequest request, HttpSession session) throws UnsupportedEncodingException {
//        deliveryAssignService.assignDelivery(request);
//
//        // Get the email from the session
//        String email = (String) session.getAttribute("userEmail");
//
//        // Encode the email to handle any special characters
//        String encodedEmail = URLEncoder.encode(email, "UTF-8");
//
//        // Redirect with the encoded email
//        return "redirect:/merchant/dashboard?email=" + encodedEmail;
//    }




    @PostMapping("/assign")
    public String assignDelivery(@ModelAttribute AssignDeliveryRequest request, HttpSession session) throws UnsupportedEncodingException {
        // Save to database
        deliveryAssignService.assignDelivery(request);

        // Get email from session
        String email = (String) session.getAttribute("userEmail");

        // Encode the email to handle special characters
        String encodedEmail = URLEncoder.encode(email, "UTF-8");

        return "redirect:/merchant/dashboard?email=" + encodedEmail;
    }




    @GetMapping("/assign-delivery-form")
        public String showAssignDeliveryForm(Model model) {
            List<User> deliveryPersons = authService.getUsersByRole(DELIVERY_PERSON);  // Fetch the list from service
            model.addAttribute("deliveryPersons", deliveryPersons);
            return "assign-delivery";  // Thymeleaf template name
        }


    @GetMapping("/person/{deliveryPersonId}")


    public ResponseEntity<List<PackageResponse>> getAssignedPackages(@PathVariable Long deliveryPersonId) {
        List<PackageResponse> packages = deliveryAssignService.getAssignmentsByDeliveryPersonId(deliveryPersonId);
        if (packages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(packages);
    }
}

