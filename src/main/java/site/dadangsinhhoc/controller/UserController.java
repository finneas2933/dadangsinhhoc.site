package site.dadangsinhhoc.controller;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.request.ValidateRequest;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.services.JwtTokenService;
import site.dadangsinhhoc.services.UserService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;


    @Autowired
    public UserController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/findUserById/{id}")
    public ResponseObject findUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/findUserByUserName/{userName}")
    public ResponseObject findUserByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    @GetMapping("/checkUserExists/{identifier}")
    public ResponseObject checkUserExists(@PathVariable String identifier) {
        return userService.checkUserExists(identifier);
    }

    @PostMapping("/createUser")
    public ResponseObject createUser(@RequestBody UserModel user) {
        return userService.createNewUser(user);
    }

//    @PostMapping("/authenticate/{username}/{password}")
//    public ResponseObject authenticate(@PathVariable String username, @PathVariable String password) {
//        return userService.authenticate(username, password);
//    }

//    @PostMapping("/validateToken")
//    public ResponseObject authenticate(@RequestBody ValidateRequest request)
//            throws ParseException, JOSEException {
//        return jwtTokenService.validateTokenResponse(request);
//    }
}
