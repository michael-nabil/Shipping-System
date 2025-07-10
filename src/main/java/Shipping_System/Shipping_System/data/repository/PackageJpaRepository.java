package Shipping_System.Shipping_System.data.repository;

import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.data.model.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PackageJpaRepository extends JpaRepository<PackageEntity, Long> {

    // Custom query to get all packages by merchantId
    List<PackageEntity> findByMerchant_Id(Long merchantId);

    // You can add more custom queries as per your requirements, for example:
    List<PackageEntity> findByStatus(PackageStatus status);
}

