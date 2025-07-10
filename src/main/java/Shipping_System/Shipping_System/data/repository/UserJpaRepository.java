package Shipping_System.Shipping_System.data.repository;



import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import Shipping_System.Shipping_System.domin.enitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
    List<UserModel> findByRole(Role role);
}
