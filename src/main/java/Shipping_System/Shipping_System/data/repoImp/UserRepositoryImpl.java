package Shipping_System.Shipping_System.data.repoImp;

import Shipping_System.Shipping_System.data.mapper.UserMapper;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.data.repository.UserJpaRepository;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import Shipping_System.Shipping_System.domin.enitiy.User;
import Shipping_System.Shipping_System.domin.repository.UserRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserJpaRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserModel> findById(Long deliveryPersonId) {
        return jpaRepository.findById(deliveryPersonId)
                .map(mapper::toDomain);
    }
    @Override
    public UserModel findByEmailNonOptional(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

   // @Override
    public UserModel save(UserModel user) {
        UserModel entity = mapper.toEntity(user);
        UserModel saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }


    @Override
    public List<UserModel> findByRole(Role role) {
        return jpaRepository.findByRole(role);
    }
    }

