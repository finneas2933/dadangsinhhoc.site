package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/current")
    public ResponseEntity<ResponseObject> getCurrentUser() {
        ResponseObject response = userService.getCurrentUser();
        return ResponseEntity.status(response.code()).body(response);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseObject deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable Integer id, @RequestBody UserModel updatedUser) {
        ResponseObject response = userService.updateUser(id, updatedUser);
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseObject> getAllUsers() {
        ResponseObject response = userService.getAllUsers();
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/countAllUsers")
    public ResponseEntity<ResponseObject> countAllUsers() {
        ResponseObject response = userService.countAllUsers();
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/countUsersByRole")
    public ResponseEntity<ResponseObject> countUsersByRole() {
        ResponseObject response = userService.countUsersByRole();
        return ResponseEntity.status(response.code()).body(response);
    }
@GetMapping("/getUsersByRole/{role}")
public ResponseEntity<ResponseObject> getUsersByRole(@PathVariable String role) {
    ResponseObject response = userService.getUsersByRole(role);
    return ResponseEntity.status(response.code()).body(response);
}
}
