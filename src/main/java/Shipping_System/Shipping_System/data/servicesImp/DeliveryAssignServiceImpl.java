package Shipping_System.Shipping_System.data.servicesImp;

import Shipping_System.Shipping_System.data.model.DeliveryAssignEntity;
import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import Shipping_System.Shipping_System.domin.repository.DeliveryAssignRepository;
import Shipping_System.Shipping_System.presentation.dto.AssignDeliveryRequest;
import Shipping_System.Shipping_System.presentation.dto.DeliveryAssignResponse;
import Shipping_System.Shipping_System.domin.service.DeliveryAssignService;
import Shipping_System.Shipping_System.presentation.dto.PackageResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAssignServiceImpl implements DeliveryAssignService {

    private final DeliveryAssignRepository deliveryAssignRepository;

    public DeliveryAssignServiceImpl(DeliveryAssignRepository deliveryAssignRepository) {
        this.deliveryAssignRepository = deliveryAssignRepository;
    }

    @Override
    public DeliveryAssignResponse assignDelivery(AssignDeliveryRequest request) {
        DeliveryAssign assigned = deliveryAssignRepository.assignDelivery(
                request.getPackageId(),
                request.getDeliveryPersonId()
        );

        return new DeliveryAssignResponse(
                assigned.getId(),
                assigned.getPackageId(),
                assigned.getDeliveryPersonId(),
                assigned.getAssignedAt()
        );
    }

    @Override
    public List<PackageResponse> getAssignmentsByDeliveryPersonId(Long deliveryPersonId) {
        // Query DeliveryAssign repository to find packages assigned to the delivery person
        List<DeliveryAssignEntity> deliveryAssigns = deliveryAssignRepository.findByDeliveryPerson_Id(deliveryPersonId);

        // Convert the entities to response DTOs
        List<PackageResponse> packageResponses = new ArrayList<>();
        for (DeliveryAssignEntity deliveryAssign : deliveryAssigns) {
            PackageEntity packageEntity = deliveryAssign.getaPackage();
            PackageResponse response = new PackageResponse(
                    Math.toIntExact(packageEntity.getId()),
                    packageEntity.getSize(),
                    packageEntity.getWeight(),
                    packageEntity.getPickupAddress(),
                    packageEntity.getDeliveryAddress(),
                    packageEntity.getStatus(),
                    Math.toIntExact(packageEntity.getMerchant().getId()) // Assuming merchant is a `UserModel` with an `id` field
            );

            packageResponses.add(response);
        }
        return packageResponses;
    }
}
