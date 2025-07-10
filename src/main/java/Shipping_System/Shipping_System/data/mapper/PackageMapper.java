package Shipping_System.Shipping_System.data.mapper;


import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.domin.enitiy.Package;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PackageMapper {

    // From JPA entity to domain model
    public static Package toDomain(PackageEntity entity) {
        if (entity == null) return null;

        Package domain = new Package();
        domain.setId(entity.getId());
        domain.setSize(entity.getSize());
        domain.setWeight(entity.getWeight());
        domain.setPickupAddress(entity.getPickupAddress());
        domain.setDeliveryAddress(entity.getDeliveryAddress());
        domain.setStatus(entity.getStatus());

        // Use long type for merchantId
        if (entity.getMerchant() != null) {
            domain.setMerchantId(entity.getMerchant().getId());
        }

        return domain;
    }

    // Update existing PackageEntity from Package (updated package)
    public void updateEntityFromDomain(Package updatedPackage, PackageEntity existingPackage, UserModel merchantEntity) {
        if (updatedPackage == null || existingPackage == null) return;

        existingPackage.setSize(updatedPackage.getSize());
        existingPackage.setWeight(updatedPackage.getWeight());
        existingPackage.setPickupAddress(updatedPackage.getPickupAddress());
        existingPackage.setDeliveryAddress(updatedPackage.getDeliveryAddress());
        existingPackage.setStatus(updatedPackage.getStatus());

        existingPackage.setMerchant(merchantEntity);  // If merchant can change, update it
    }
    // From domain model to JPA entity
    public static PackageEntity toEntity(Package domain, UserModel merchantEntity) {
        if (domain == null) return null;

        PackageEntity entity = new PackageEntity();
        entity.setId(domain.getId());
        entity.setSize(domain.getSize());
        entity.setWeight(domain.getWeight());
        entity.setPickupAddress(domain.getPickupAddress());
        entity.setDeliveryAddress(domain.getDeliveryAddress());
        entity.setStatus(domain.getStatus());

        entity.setMerchant(merchantEntity);

        return entity;
    }


    public static List<Package> toDomainList(List<PackageEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(PackageMapper::toDomain)
                .collect(Collectors.toList());
    }


}
