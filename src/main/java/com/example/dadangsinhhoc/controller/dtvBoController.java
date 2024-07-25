package com.example.dadangsinhhoc.controller;

import com.example.dadangsinhhoc.exception.BadResourceException;
import com.example.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import com.example.dadangsinhhoc.exception.ResourceNotFoundException;
import com.example.dadangsinhhoc.models.dtvBo;
import com.example.dadangsinhhoc.models.responseObject;
import com.example.dadangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
public class dtvBoController {
    @Autowired
    private dtvBoRepository dtvBoRepository;
    @Autowired
    private com.example.dadangsinhhoc.services.dtvBoService dtvBoService;

    @GetMapping("/getAllBo")
    public responseObject getAllBo() {
        try {
            List<dtvBo> dtvBo = dtvBoService.getAllBo();
            return new responseObject("200", "Query All DTV Bo", dtvBo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new responseObject("500", "Internal Server Error", null)).getBody();
        }
    }

    @GetMapping("/getBoById/{id}")
    public ResponseEntity<responseObject> getBoById(@PathVariable long id) {
        try {
            Optional<dtvBo> dtvBo = dtvBoService.findById(id);
            return ResponseEntity.ok(new responseObject("200", "Query DTV Bo by ID", dtvBo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new responseObject("500", "Internal Server Error", null));
        }
    }

    @GetMapping("/countAllBo")
    public ResponseEntity<responseObject> countAllBo() {
        try {
            long count = dtvBoService.countAllBo();
            return ResponseEntity.ok(new responseObject("200", "Count All DTV Bo", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new responseObject("500", "Internal Server Error", null));
        }
    }

    @PostMapping("/addNewBo")
    public ResponseEntity<responseObject> addNewBo(@RequestBody dtvBo dtvBo) {
        try {
            dtvBo savedBo = dtvBoService.saveBo(dtvBo);
            return new ResponseEntity<>(new responseObject("200", "Successfully added", savedBo), HttpStatus.CREATED);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(new responseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<responseObject> updateBo(@RequestBody dtvBo dtvBo) {
        try {
            dtvBoService.updateBo(dtvBo);
            return new ResponseEntity<>(new responseObject("200", "Successfully updated", dtvBo), HttpStatus.OK);
        } catch (BadResourceException | ResourceNotFoundException e) {
            return new ResponseEntity<>(new responseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<responseObject> deleteBo(@PathVariable Long id) {
        try {
            dtvBoService.deleteByIdBo(id);
            return new ResponseEntity<>(new responseObject("200", "Successfully deleted", null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new responseObject("404", e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

}
