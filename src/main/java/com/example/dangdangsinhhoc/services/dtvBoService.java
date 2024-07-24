package com.example.dangdangsinhhoc.services;

import com.example.dangdangsinhhoc.models.dtv_bo;
import com.example.dangdangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dtvBoService {
    @Autowired
    private dtvBoRepository dtvBoRepository;

    public List<dtv_bo> getAllBo() {
        return dtvBoRepository.findAll();
    }
}
