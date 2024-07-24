package com.example.user_management.controller;

import com.example.user_management.models.Company;
import com.example.user_management.models.User;
import com.example.user_management.models.responseObject;
import com.example.user_management.repositories.companyRepository;
import com.example.user_management.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class companyController {
    @Autowired
    private companyRepository companyRepository;
    @Autowired
    private com.example.user_management.services.companyService companyService;

    @GetMapping("/getInforCompany")
    public responseObject getAllCompany() {
        List<Company> companies = companyService.getAllCompany();
        return new responseObject("200", "Query Users In Company Success", companies);
    }

    @PostMapping("/insertUserToCompany")
    ResponseEntity<responseObject> insertUser(@RequestBody Company addUserToCompany) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new responseObject("200", "Query All Users Success", companyRepository.save(addUserToCompany))
        );
    }

    @DeleteMapping("/deleteUserToCompany/{id}")
    ResponseEntity<responseObject> deletoUserOutCompany(@PathVariable Long id) {
        boolean exists = companyRepository.existsById(id);
        if(exists) {
            companyRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new responseObject("200", "Success", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new responseObject("False", "Cannot find User to delete!", "")
        );
    }
}
