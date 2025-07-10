package Shipping_System.Shipping_System.domin.repository;

import Shipping_System.Shipping_System.data.model.DeliveryAssignEntity;
import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import Shipping_System.Shipping_System.presentation.dto.AssignDeliveryRequest;
import Shipping_System.Shipping_System.presentation.dto.DeliveryAssignResponse;

import java.util.List;

public interface DeliveryAssignRepository {
    DeliveryAssign assignDelivery(Long packageId, Long deliveryPersonId);
    List<DeliveryAssign> getAssignmentsByDeliveryPersonId(Long deliveryPersonId);

    List<DeliveryAssignEntity> findByDeliveryPerson_Id(Long deliveryPersonId);
    DeliveryAssignResponse assignDelivery(AssignDeliveryRequest request);
}

