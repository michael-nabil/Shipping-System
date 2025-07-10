package Shipping_System.Shipping_System.domin.repository;


import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.domin.enitiy.Package;
import java.util.List;

public interface PackageRepository {

    Package save(Package packageDomain);

    List<Package> findByMerchantId(long merchantId);

    List<Package> findByStatus(PackageStatus status);

    Package findById(long id);

    Package updateStatus(long id, PackageStatus status);
}
