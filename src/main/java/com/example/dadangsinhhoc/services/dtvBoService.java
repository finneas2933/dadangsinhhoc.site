package com.example.dadangsinhhoc.services;

import com.example.dadangsinhhoc.models.dtvBo;
import com.example.dadangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dtvBoService {
    @Autowired
    private dtvBoRepository dtvBoRepository;

    public List<dtvBo> getAllBo() {
        return dtvBoRepository.findAll();
    }
}
