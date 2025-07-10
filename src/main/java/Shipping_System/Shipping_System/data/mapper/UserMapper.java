package Shipping_System.Shipping_System.data.mapper;
import Shipping_System.Shipping_System.*;
import Shipping_System.Shipping_System.data.model.UserModel;
import Shipping_System.Shipping_System.domin.enitiy.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserModel toDomain(UserModel entity) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        return user;
    }

    public UserModel toEntity(UserModel user) {
        UserModel entity = new UserModel();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }



    public User tooDomain(UserModel model) {
        User user = new User();
        user.setId(model.getId());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        user.setRole(model.getRole());
        return user;
    }

    public UserModel tooEntity(User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setEmail(user.getEmail());
        model.setPassword(user.getPassword());
        model.setRole(user.getRole());
        return model;
    }
    }

