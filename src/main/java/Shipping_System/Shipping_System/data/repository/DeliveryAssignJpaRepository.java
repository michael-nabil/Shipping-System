package Shipping_System.Shipping_System.data.repository;

import Shipping_System.Shipping_System.data.model.DeliveryAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAssignJpaRepository extends JpaRepository<DeliveryAssignEntity, Long> {
    List<DeliveryAssignEntity> findByDeliveryPerson_Id(Long deliveryPersonId);
}
