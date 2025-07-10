package Shipping_System.Shipping_System.domin.service;

import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.domin.enitiy.Role;
import Shipping_System.Shipping_System.domin.enitiy.User;

import java.util.List;

public interface AuthService {
    UserModel register(UserModel user);
    UserModel login(String email, String password);
    //void logout(String token);
    UserModel getCurrentUser(String token);
    public List<User> getUsersByRole(Role role);
    String getEmailById(Long deliveryPersonId);
}