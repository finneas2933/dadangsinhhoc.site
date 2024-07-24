package com.example.user_management.services;

import com.example.user_management.models.Company;
import com.example.user_management.models.User;
import com.example.user_management.repositories.companyRepository;
import com.example.user_management.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class companyService {
    @Autowired
    private companyRepository companyRepository;

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
}
