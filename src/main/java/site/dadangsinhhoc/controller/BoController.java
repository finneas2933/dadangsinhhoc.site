package site.dadangsinhhoc.controller;

import site.dadangsinhhoc.exception.BadResourceException;
import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import site.dadangsinhhoc.exception.ResourceNotFoundException;
import site.dadangsinhhoc.models.BoModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.repositories.BoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
public class BoController {
    @Autowired
    private BoRepository BoRepository;
    @Autowired
    private site.dadangsinhhoc.services.BoService BoService;

    @GetMapping("/getAllBo")
    public ResponseObject getAllBo() {
        try {
            List<BoModel> BoModel = BoService.getAllBo();
            return new ResponseObject("200", "Query All DTV Bo", BoModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("500", "Internal Server Error", null)).getBody();
        }
    }

    @GetMapping("/getBoById/{id}")
    public ResponseEntity<ResponseObject> getBoById(@PathVariable long id) {
        try {
            Optional<BoModel> dtvBo = BoService.findById(id);
            return ResponseEntity.ok(new ResponseObject("200", "Query DTV Bo by ID", dtvBo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("500", "Internal Server Error", null));
        }
    }

    @GetMapping("/countAllBo")
    public ResponseEntity<ResponseObject> countAllBo() {
        try {
            long count = BoService.countAllBo();
            return ResponseEntity.ok(new ResponseObject("200", "Count All DTV Bo", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("500", "Internal Server Error", null));
        }
    }

    @PostMapping("/addNewBo")
    public ResponseEntity<ResponseObject> addNewBo(@RequestBody BoModel BoModel) {
        try {
            BoModel savedBo = BoService.saveBo(BoModel);
            return new ResponseEntity<>(new ResponseObject("200", "Successfully added", savedBo), HttpStatus.CREATED);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(new ResponseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateBo(@RequestBody BoModel BoModel) {
        try {
            BoService.updateBo(BoModel);
            return new ResponseEntity<>(new ResponseObject("200", "Successfully updated", BoModel), HttpStatus.OK);
        } catch (BadResourceException | ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteBo(@PathVariable Long id) {
        try {
            BoService.deleteByIdBo(id);
            return new ResponseEntity<>(new ResponseObject("200", "Successfully deleted", null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseObject("404", e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

}
