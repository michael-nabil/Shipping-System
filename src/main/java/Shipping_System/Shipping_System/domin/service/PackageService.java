package Shipping_System.Shipping_System.domin.service;

import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.domin.enitiy.Package;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface PackageService {
    public void deletePackage(Long packageId);
    public void updatePackage(Long id, Package updatedPackage,Long merchid);
    Package createPackage(Package packageDomain, long merchantId);
    List<Package> getPackagesByMerchant(long merchantId);
    List<Package> getPackagesByStatus(PackageStatus status);
    Package getPackageById(long id);

    public Package updatePackageStatus(long packageId, PackageStatus status, long deliveryPersonId);
}
