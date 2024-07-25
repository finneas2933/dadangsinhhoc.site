package com.example.dadangsinhhoc.controller;

import com.example.dadangsinhhoc.models.dtvBo;
import com.example.dadangsinhhoc.models.responseObject;
import com.example.dadangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class dtvBoController {
    @Autowired
    private dtvBoRepository dtvBoRepository;
    @Autowired
    private com.example.dadangsinhhoc.services.dtvBoService dtvBoService;

    @GetMapping("/getAllBo")
    public responseObject getAllBo() {
        List<dtvBo> dtvBo = dtvBoService.getAllBo();
        return new responseObject("200", "Query All DTV Bo", dtvBo);
    }


}
