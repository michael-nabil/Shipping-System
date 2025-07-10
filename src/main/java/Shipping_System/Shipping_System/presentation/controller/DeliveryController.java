package Shipping_System.Shipping_System.presentation.controller;

import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.domin.service.AuthService;
import Shipping_System.Shipping_System.domin.service.DeliveryAssignService;
import Shipping_System.Shipping_System.domin.service.PackageService;
import Shipping_System.Shipping_System.presentation.dto.PackageResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryAssignService deliveryAssignService;
    private final PackageService PackageService;
    private final AuthService authService;

    public DeliveryController(DeliveryAssignService deliveryAssignService, PackageService packageService, AuthService authService) {
        this.deliveryAssignService = deliveryAssignService;
        PackageService = packageService;
        this.authService = authService;
    }



    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long deliveryPersonId = (Long) session.getAttribute("userId");

        if (deliveryPersonId == null) {
            return "redirect:/auth/login";
        }

        List<PackageResponse> assignedPackages = deliveryAssignService.getAssignmentsByDeliveryPersonId(deliveryPersonId);
        String email = authService.getEmailById(deliveryPersonId);  // use injected instance, not static

        model.addAttribute("email", email);
        model.addAttribute("assignedPackages", assignedPackages);

        return "delivery-dashboard";
    }


    //add
@GetMapping("/orders")
public String viewOrders(HttpSession session, Model model) {
    Long deliveryPersonId = (Long) session.getAttribute("userId");

    if (deliveryPersonId == null) {
        return "redirect:/auth/login"; // Redirect to login if session is missing
    }

    List<PackageResponse> packages = deliveryAssignService.getAssignmentsByDeliveryPersonId(deliveryPersonId);
    model.addAttribute("packages", packages);

    return "delivery-orders"; // This should match the Thymeleaf HTML file name
}

//add



    @GetMapping("/status")
    public String showUpdateStatusForm(@RequestParam("packageId") Long packageId, Model model) {
        model.addAttribute("packageId", packageId);
        model.addAttribute("statuses", PackageStatus.values());
        return "delivery-update-status"; // points to delivery-update-status.html
    }

    @PostMapping("/status/update")
    public String updateOrderStatus(@RequestParam Long packageId,
                                    @RequestParam PackageStatus status,
                                    HttpSession session) {
        Long deliveryPersonId = (Long) session.getAttribute("userId");

        if (deliveryPersonId == null) {
            return "redirect:/auth/login";
        }

        PackageService.updatePackageStatus(packageId, status, deliveryPersonId);
        return "redirect:/delivery/orders"; // or dashboard if you prefer
    }


}