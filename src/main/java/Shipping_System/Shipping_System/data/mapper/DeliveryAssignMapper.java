package Shipping_System.Shipping_System.data.mapper;

import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import Shipping_System.Shipping_System.data.model.*;

public class DeliveryAssignMapper {

    public static DeliveryAssign toDomain(DeliveryAssignEntity entity) {
        return new DeliveryAssign(
                entity.getId(),
                entity.getaPackage().getId(),
                entity.getDeliveryPerson().getId(),
                entity.getAssignedAt()
        );
    }

    public static DeliveryAssignEntity toEntity(DeliveryAssign domain, PackageEntity packageEntity, UserModel deliveryPerson) {
        DeliveryAssignEntity entity = new DeliveryAssignEntity();
        entity.setId(domain.getId());
        entity.setaPackage(packageEntity);
        entity.setDeliveryPerson(deliveryPerson);
        entity.setAssignedAt(domain.getAssignedAt());
        return entity;
    }
}
