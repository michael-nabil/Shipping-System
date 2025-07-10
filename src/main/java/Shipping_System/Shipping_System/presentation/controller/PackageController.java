package Shipping_System.Shipping_System.presentation.controller;

import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.domin.service.PackageService;
import Shipping_System.Shipping_System.presentation.dto.CreatePackageRequest;
import Shipping_System.Shipping_System.domin.enitiy.Package;
import Shipping_System.Shipping_System.presentation.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/add")
    public String showCreatePackageForm(Model model,HttpSession session) {
        long merchantId = (Long) session.getAttribute("userId");// احصل على merchantId من الخدمة
        model.addAttribute("formData", new CreatePackageRequest());
        model.addAttribute("merchantId", merchantId);
        return "add_package";
    }

    @PostMapping("/add")
    public String createPackage(@ModelAttribute CreatePackageRequest formData, HttpSession session,Model model) {
        Long merchantId = (Long) session.getAttribute("userId");
        String email = (String) session.getAttribute("userEmail");


        Package newPackage = formData.toDomainModel();
        newPackage.setStatus(PackageStatus.PENDING);

        Package createdPackage = packageService.createPackage(newPackage, merchantId);

        model.addAttribute("message", "Package created successfully with ID: " + createdPackage.getId());

        return "redirect:/merchant/dashboard?email=" + email;
    }

    // Display the package details in the form for editing
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Package packageToEdit = packageService.getPackageById(id);
        model.addAttribute("package", packageToEdit);

        return "edit-package"; // The view for the form
    }

//    // Handle the package update submission
//    @PostMapping("/update/{id}")
//    public String updatePackage(@PathVariable Long id, @ModelAttribute("package") Package packageToUpdate,HttpSession session) {
//        // Update the package with the new data
//        // Retrieve the merchantId from the session
//        Long merchantId = (Long) session.getAttribute("merchantId");
//
//        if (merchantId == null) {
//            throw new ResourceNotFoundException("Merchant not found in session");
//        }
//        packageService.updatePackage(id, packageToUpdate,merchantId);
//        return "redirect:/packages/view"; // Redirect to the package list after the update
//    }

    @PostMapping("/update/{id}")
    public String updatePackage(@PathVariable Long id,
                                @ModelAttribute("package") Package packageToUpdate,
                                HttpSession session) {
        // Retrieve the merchantId from the session
        Long merchantId = (Long) session.getAttribute("userId");
        String email = (String) session.getAttribute("userEmail");

        // If the merchantId is not found in session, redirect to an error or login page
        if (merchantId == null) {
            return "redirect:/login?error=merchant-not-found";  // Example: redirect to login with an error message
        }

        // Proceed to update the package
        packageService.updatePackage(id, packageToUpdate, merchantId);


        return "redirect:/merchant/dashboard?email=" + email;
    }


    @GetMapping("/view")
    public String viewAllPackages(Model model, HttpSession session) {
        Long merchantId = (Long) session.getAttribute("userId");
    List<Package> packages = packageService.getPackagesByMerchant(merchantId); // adjust logic as needed
        model.addAttribute("packages", packages);
        return "view-packages";
    }

    @GetMapping("/confirm-delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Package packageToDelete = packageService.getPackageById(id);
        if (packageToDelete == null) {
            // Handle case where package is not found
            return "redirect:/packages/view"; // Or show an error page
        }
        model.addAttribute("package", packageToDelete);
        return "confirm-delete";
    }


    @PostMapping("/delete/{id}")
    public String deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return "redirect:/packages/view"; // Redirect to the list of packages after deletion
    }

    @GetMapping("/merchant/{merchantId}")
    @ResponseBody
    public List<Package> getPackagesByMerchant(@PathVariable long merchantId) {
        return packageService.getPackagesByMerchant(merchantId);
    }

    @GetMapping("/{id:[0-9]+}")
    @ResponseBody
    public Package getPackageById(@PathVariable long id) {
        return packageService.getPackageById(id);
    }


}
