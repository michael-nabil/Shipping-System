package Shipping_System.Shipping_System.data.servicesImp;

import Shipping_System.Shipping_System.data.mapper.UserMapper;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.data.repoImp.UserRepositoryImpl;

import Shipping_System.Shipping_System.domin.enitiy.User;
import org.springframework.stereotype.Service;

import  Shipping_System.Shipping_System.domin.service.AuthService;
import  Shipping_System.Shipping_System.presentation.exception.AuthException;

import java.util.List;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;
    public AuthServiceImpl(UserRepositoryImpl userRepository, UserMapper userMapper){

        this.userRepository = userRepository;

        this.userMapper = userMapper;
    }

    @Override
    public UserModel register(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AuthException("Email already in use");
        }

        return userRepository.save(user);
    }

    @Override
    public UserModel login(String email, String password) {
        UserModel user = userRepository.findByEmailNonOptional(email);

        if (!user.getPassword().equals(password)) {
            throw new AuthException("Invalid email or password");
        }

        return user;
    }

    // @Override
//    public void logout(String token) {
//        //
//    }

    @Override
    public UserModel getCurrentUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("User not found"));
    }
    @Override
    public List<User> getUsersByRole(Role role) {
        List<UserModel> userModels = userRepository.findByRole(role); // from DB
        return userModels.stream()
                .map(userMapper::tooDomain) // map to domain User
                .collect(Collectors.toList());
    }
    @Override
    public  String getEmailById(Long deliveryPersonId) {
        return userRepository.findById(deliveryPersonId)
                .map(UserModel::getEmail)
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));
    }

}