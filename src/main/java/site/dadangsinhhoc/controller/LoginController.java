package site.dadangsinhhoc.controller;//package com.example.dadangsinhhoc.controller;
//import com.example.dadangsinhhoc.models.userModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//
//
//@Controller
//public class loginController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping(value={"/", "/login"})
//    public ModelAndView login(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
//
//    @GetMapping(value="/registration")
//    public ModelAndView registration(){
//        ModelAndView modelAndView = new ModelAndView();
//        userModel user = new userModel();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/registration")
//    public ModelAndView createNewUser(@Valid userModel user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        userModel userExists = userService.findUserByUserName(user.getUserName());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("userName", "error.user",
//                            "There is already a user registered with the user name provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new userModel());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }
//
//    @GetMapping(value="/admin/home")
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        userModel user = userService.findUserByUserName(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUserName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
//}
