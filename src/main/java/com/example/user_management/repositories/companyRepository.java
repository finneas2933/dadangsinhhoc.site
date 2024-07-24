package com.example.user_management.repositories;

import com.example.user_management.models.Company;
import com.example.user_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface companyRepository extends JpaRepository <Company, Long> {
}
