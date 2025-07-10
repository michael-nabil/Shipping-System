package Shipping_System.Shipping_System.data.repoImp;

import Shipping_System.Shipping_System.data.mapper.PackageMapper;
import Shipping_System.Shipping_System.data.model.PackageEntity;
import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.data.repository.PackageJpaRepository;
import Shipping_System.Shipping_System.domin.enitiy.Package;
import Shipping_System.Shipping_System.domin.repository.PackageRepository;
import Shipping_System.Shipping_System.domin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    @Autowired
    private PackageJpaRepository packageJpaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageMapper packageMapper;

    @Override
    public Package save(Package packageDomain) {
        // Get the merchant from DB
        UserModel merchant = userRepository.findById(packageDomain.getMerchantId())
                .orElseThrow(() -> new NoSuchElementException("Merchant not found with ID: " + packageDomain.getMerchantId()));

        // Convert domain to entity and save
        PackageEntity entity = packageMapper.toEntity(packageDomain, merchant);
        PackageEntity saved = packageJpaRepository.save(entity);

        return packageMapper.toDomain(saved);
    }

    @Override
    public List<Package> findByMerchantId(long merchantId) {
        List<PackageEntity> entities = packageJpaRepository.findByMerchant_Id(merchantId);
        return packageMapper.toDomainList(entities);
    }

    @Override
    public List<Package> findByStatus(PackageStatus status) {
        List<PackageEntity> entities = packageJpaRepository.findByStatus(status);
        return packageMapper.toDomainList(entities);
    }

    @Override
    public Package findById(long id) {
        PackageEntity entity = packageJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Package not found with ID: " + id));
        return packageMapper.toDomain(entity);
    }

    @Override
    public Package updateStatus(long id, PackageStatus status) {
        PackageEntity entity = packageJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Package not found with ID: " + id));

        entity.setStatus(status);
        PackageEntity updated = packageJpaRepository.save(entity);

        return packageMapper.toDomain(updated);
    }
}
