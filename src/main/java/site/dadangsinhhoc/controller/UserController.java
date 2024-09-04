package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findUserById/{id}")
    public ResponseObject findUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseObject findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/checkUserExists/{identifier}")
    public ResponseObject checkUserExists(@PathVariable String identifier) {
        return userService.checkUserExists(identifier);
    }

    @PostMapping("/createUser")
    public ResponseObject createUser(@RequestBody UserModel user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    public ResponseObject login(@RequestBody UserModel loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
