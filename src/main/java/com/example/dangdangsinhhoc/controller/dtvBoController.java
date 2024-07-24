package com.example.dangdangsinhhoc.controller;

import com.example.dangdangsinhhoc.models.dtv_bo;
import com.example.dangdangsinhhoc.models.responseObject;
import com.example.dangdangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class dtvBoController {
    @Autowired
    private dtvBoRepository dtvBoRepository;
    @Autowired
    private com.example.dangdangsinhhoc.services.dtvBoService dtvBoService;

    @GetMapping("/getBo")
    public responseObject getAllUser() {
        List<dtv_bo> users = dtvBoService.getAllBo();
        return new responseObject("200", "Query All DTV Bo", users);
    }

//    @PostMapping("/insertUser")
//    ResponseEntity<responseObject> insertUser(@RequestBody User newUser) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new responseObject("200", "Query All Users Success", dtvBoRepository.save(newUser))
//        );
//    }
}
