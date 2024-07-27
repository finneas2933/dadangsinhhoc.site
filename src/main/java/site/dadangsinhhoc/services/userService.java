package site.dadangsinhhoc.services;//package com.example.dadangsinhhoc.services;
//
//import com.example.dadangsinhhoc.models.userModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.example.dadangsinhhoc.repositories.userRepository
//
//
//@Service
//public class userService {
//
//    private userRepository userRepository;
//    private RoleRepository roleRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public UserService(userRepository userRepository,
//                       RoleRepository roleRepository,
//                       BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    public userModel findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public userModel findUserByUserName(String userName) {
//        return userRepository.findByUserName(userName);
//    }
//
//    public userModel saveUser(userModel user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }
//}
