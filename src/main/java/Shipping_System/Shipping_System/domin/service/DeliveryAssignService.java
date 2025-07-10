package Shipping_System.Shipping_System.domin.service;

import Shipping_System.Shipping_System.presentation.dto.AssignDeliveryRequest;
import Shipping_System.Shipping_System.presentation.dto.DeliveryAssignResponse;
import Shipping_System.Shipping_System.presentation.dto.PackageResponse;

import java.util.List;

public interface DeliveryAssignService {
    DeliveryAssignResponse assignDelivery(AssignDeliveryRequest request);
    List<PackageResponse> getAssignmentsByDeliveryPersonId(Long deliveryPersonId);
}

