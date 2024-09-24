package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;

@Service
public interface IUserService {

     ResponseObject findById(Integer id);

     ResponseObject findByEmail(String email);

     ResponseObject checkUserExists(String identifier);

     ResponseObject createNewUser(UserModel user);

     ResponseObject login(String email, String password);

     ResponseObject getCurrentUser();

     ResponseObject deleteUser(int id);

     ResponseObject updateUser(Integer id, UserModel updatedUser);

     ResponseObject getAllUsers();

     ResponseObject countAllUsers();

     ResponseObject countUsersByRole();

     ResponseObject getUsersByRole(String role);
}
