package Shipping_System.Shipping_System.data.servicesImp;



import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.data.repoImp.DeliveryAssignRepositoryImpl;
import Shipping_System.Shipping_System.domin.enitiy.DeliveryAssign;
import  Shipping_System.Shipping_System.domin.enitiy.Package;

import Shipping_System.Shipping_System.domin.repository.UserRepository;
import Shipping_System.Shipping_System.domin.service.PackageService;
import  Shipping_System.Shipping_System.data.repository.PackageJpaRepository;
import  Shipping_System.Shipping_System.data.mapper.PackageMapper;
import Shipping_System.Shipping_System.presentation.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {


    private PackageJpaRepository packageJpaRepository; // Repository layer

   private  UserRepository userRepository;
    private PackageMapper packageMapper; // Mapper for entity/domain conversion
private DeliveryAssignServiceImpl deliveryAssignService;
    private DeliveryAssignRepositoryImpl deliveryAssignRepository;
    public PackageServiceImpl(PackageJpaRepository packageJpaRepository,DeliveryAssignRepositoryImpl deliveryAssignRepository ,UserRepository userRepository, PackageMapper packageMapper, DeliveryAssignServiceImpl deliveryAssignService) {
        this.packageJpaRepository = packageJpaRepository;
        this.userRepository = userRepository;
        this.packageMapper = packageMapper;
        this.deliveryAssignService = deliveryAssignService;
        this.deliveryAssignRepository=deliveryAssignRepository;
    }

    @Override

    public Package createPackage(Package packageDomain, long merchantId) {
        // Fetch the merchant from the repository using the merchantId

        UserModel merchant = userRepository.findById(merchantId)
                .orElseThrow(() -> new EntityNotFoundException("Merchant not found with ID: " + merchantId));

        // Convert the domain model to the entity model
        PackageEntity packageEntity = PackageMapper.toEntity(packageDomain, merchant);

        // Save the entity in the repository
        packageEntity = packageJpaRepository.save(packageEntity);

        // Convert the saved entity back to the domain model and return it
        return PackageMapper.toDomain(packageEntity);
    }

    @Override
    public List<Package> getPackagesByMerchant(long merchantId) {
        List<PackageEntity> packageEntities = packageJpaRepository.findByMerchant_Id(merchantId);
        return PackageMapper.toDomainList(packageEntities);
    }

    @Override
    public List<Package> getPackagesByStatus(PackageStatus status) {
        List<PackageEntity> packageEntities = packageJpaRepository.findByStatus(status);
        return packageMapper.toDomainList(packageEntities);
    }

    @Override
    public Package getPackageById(long id) {
        Optional<PackageEntity> packageEntityOpt = packageJpaRepository.findById(id);
        if (packageEntityOpt.isPresent()) {
            return packageMapper.toDomain(packageEntityOpt.get());
        } else {
            throw new RuntimeException("Package not found");
        }
    }
    @Override
    public void deletePackage(Long packageId) {
        packageJpaRepository.deleteById(packageId);
    }

    @Override
    public void updatePackage(Long id, Package updatedPackage, Long merchid) {
        // Fetch the existing package
        PackageEntity existingPackage = packageJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package not found with id: " + id));



        // Fetch the merchant entity using the merchantId
        UserModel merchantEntity = userRepository.findById(merchid)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant not found"));

        // Update the package details
        existingPackage.setSize(updatedPackage.getSize());
        existingPackage.setWeight(updatedPackage.getWeight());
        existingPackage.setPickupAddress(updatedPackage.getPickupAddress());
        existingPackage.setDeliveryAddress(updatedPackage.getDeliveryAddress());
        existingPackage.setStatus(updatedPackage.getStatus());
        existingPackage.setMerchant(merchantEntity);

        // Save the updated package
        packageJpaRepository.save(existingPackage);
    }

    @Override
    public Package updatePackageStatus(long packageId, PackageStatus status, long deliveryPersonId) {
        // Get all assignments for the delivery person
        List<DeliveryAssign> assignments = deliveryAssignRepository.getAssignmentsByDeliveryPersonId(deliveryPersonId);

        boolean found = false;

        for (DeliveryAssign assign : assignments) {
            if (assign.getPackageId() == packageId) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Unauthorized: Package not assigned to this delivery person.");
        }

        // Update the package status
        PackageEntity packageEntity = packageJpaRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        packageEntity.setStatus(status);
        PackageEntity updated = packageJpaRepository.save(packageEntity);

        return packageMapper.toDomain(updated);
    }

}

