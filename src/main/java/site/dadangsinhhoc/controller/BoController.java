package site.dadangsinhhoc.controller;

import site.dadangsinhhoc.exception.BadResourceException;
import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import site.dadangsinhhoc.exception.ResourceNotFoundException;
import site.dadangsinhhoc.models.boModel;
import site.dadangsinhhoc.models.responseObject;
import site.dadangsinhhoc.repositories.dtvBoRepository;
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
    private site.dadangsinhhoc.services.dtvBoService dtvBoService;

    @GetMapping("/getAllBo")
    public responseObject getAllBo() {
        try {
            List<boModel> boModel = dtvBoService.getAllBo();
            return new responseObject("200", "Query All DTV Bo", boModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new responseObject("500", "Internal Server Error", null)).getBody();
        }
    }

    @GetMapping("/getBoById/{id}")
    public ResponseEntity<responseObject> getBoById(@PathVariable long id) {
        try {
            Optional<boModel> dtvBo = dtvBoService.findById(id);
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
    public ResponseEntity<responseObject> addNewBo(@RequestBody boModel boModel) {
        try {
            boModel savedBo = dtvBoService.saveBo(boModel);
            return new ResponseEntity<>(new responseObject("200", "Successfully added", savedBo), HttpStatus.CREATED);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(new responseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<responseObject> updateBo(@RequestBody boModel boModel) {
        try {
            dtvBoService.updateBo(boModel);
            return new ResponseEntity<>(new responseObject("200", "Successfully updated", boModel), HttpStatus.OK);
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
