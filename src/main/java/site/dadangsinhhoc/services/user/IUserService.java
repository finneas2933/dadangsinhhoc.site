package site.dadangsinhhoc.services.user;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.LoginDto;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;

import java.time.LocalDate;

@Service
public interface IUserService {

     ResponseObject findById(Integer id);

     ResponseObject findByEmail(String email);

     ResponseObject checkUserExists(String identifier);

     ResponseObject createNewUser(String name, String email, String pw, String re_pw, String phone, String gender, LocalDate dob, String address);

     ResponseObject login(String email, String password);

     ResponseObject getCurrentUser();

     ResponseObject deleteUser(int id);

     ResponseObject updateUser(Integer id, UserModel updatedUser);

     ResponseObject getAllUsers();

     ResponseObject countAllUsers();

     ResponseObject countUsersByRole();

     ResponseObject getUsersByRole(String role);
}
