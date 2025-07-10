package Shipping_System.Shipping_System.data.repoImp;

import Shipping_System.Shipping_System.data.repository.*;
import Shipping_System.Shipping_System.data.model.*;
import Shipping_System.Shipping_System.data.mapper.DeliveryAssignMapper;
import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import Shipping_System.Shipping_System.domin.repository.DeliveryAssignRepository;
import Shipping_System.Shipping_System.domin.repository.UserRepository;
import Shipping_System.Shipping_System.presentation.dto.AssignDeliveryRequest;
import Shipping_System.Shipping_System.presentation.dto.DeliveryAssignResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeliveryAssignRepositoryImpl implements DeliveryAssignRepository {

    private final DeliveryAssignJpaRepository deliveryAssignJpaRepository;
    private final PackageJpaRepository packageJpaRepository;
    private final UserRepository userRepository;

    public DeliveryAssignRepositoryImpl(DeliveryAssignJpaRepository deliveryAssignJpaRepository,
                                        PackageJpaRepository packageJpaRepository,
                                        UserRepository userRepository) {
        this.deliveryAssignJpaRepository = deliveryAssignJpaRepository;
        this.packageJpaRepository = packageJpaRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public DeliveryAssign assignDelivery(Long packageId, Long deliveryPersonId) {
//        PackageEntity packageEntity = packageJpaRepository.findById(packageId).orElseThrow();
//        UserModel deliveryPerson = (UserModel) userRepository.findById(deliveryPersonId).orElseThrow();
//
//        DeliveryAssignEntity entity = new DeliveryAssignEntity();
//        entity.setaPackage(packageEntity);
//        entity.setDeliveryPerson(deliveryPerson);
//        entity.setAssignedAt(LocalDateTime.now());
//
//        return DeliveryAssignMapper.toDomain(deliveryAssignJpaRepository.save(entity));
//    }
@Override
public DeliveryAssignResponse assignDelivery(AssignDeliveryRequest request) {
    // Fetch related entities
    PackageEntity pkg = packageJpaRepository.findById(request.getPackageId())
            .orElseThrow(() -> new RuntimeException("Package not found"));

    UserModel deliveryPerson = userRepository.findById(request.getDeliveryPersonId())
            .orElseThrow(() -> new RuntimeException("Delivery person not found"));

    // Create entity
    DeliveryAssignEntity assigned = new DeliveryAssignEntity();
    assigned.setaPackage(pkg);
    assigned.setDeliveryPerson(deliveryPerson);
    assigned.setAssignedAt(LocalDateTime.now());

    // Save to DB
    DeliveryAssignEntity saved = deliveryAssignJpaRepository.save(assigned);

    // Return response
    return new DeliveryAssignResponse(
            saved.getId(),
            saved.getaPackage().getId(),
            saved.getDeliveryPerson().getId(),
            saved.getAssignedAt()
    );
}


    @Override
    public DeliveryAssign assignDelivery(Long packageId, Long deliveryPersonId) {
        return null;
    }

    @Override
    public List<DeliveryAssign> getAssignmentsByDeliveryPersonId(Long deliveryPersonId) {
        return deliveryAssignJpaRepository.findByDeliveryPerson_Id(deliveryPersonId).stream()
                .map(DeliveryAssignMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Override
    public List<DeliveryAssignEntity> findByDeliveryPerson_Id(Long deliveryPersonId) {
        return deliveryAssignJpaRepository.findByDeliveryPerson_Id(deliveryPersonId);
    }
}
