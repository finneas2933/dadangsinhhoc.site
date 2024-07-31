//package site.dadangsinhhoc.controller;
//
//import site.dadangsinhhoc.exception.BadResourceException;
//import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
//import site.dadangsinhhoc.exception.ResourceNotFoundException;
//import site.dadangsinhhoc.models.BoModel;
//import site.dadangsinhhoc.models.NganhModel;
//import site.dadangsinhhoc.models.ResponseObject;
//import site.dadangsinhhoc.repositories.NganhRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//import site.dadangsinhhoc.repositories.NganhRepository;
//import site.dadangsinhhoc.services.NganhService;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//public class NganhController {
//    @Autowired
//    private NganhRepository NganhRepository;
//
//    @Autowired
//    private site.dadangsinhhoc.services.NganhService NganhService;
//
//    @GetMapping("/getAllNganh")
//    public ResponseObject getAllNganh() {
//        try {
//            List<NganhModel> NganhModel = NganhService.getAllNganh();
//            return new ResponseObject("200", "Query All Nganh", NganhModel);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseObject("500", "Internal Server Error", null)).getBody();
//        }
//    }
//
//    @GetMapping("/getNganhById/{id}")
//    public ResponseEntity<ResponseObject> getNganhById(@PathVariable long id) {
//        try {
//            Optional<NganhModel> dtvNganh = NganhService.findById(id);
//            return dtvNganh.map(value -> ResponseEntity.ok(new ResponseObject("200", "Query Nganh by ID", value)))
//                    .orElseGet(() -> ResponseEntity.status(404).body(new ResponseObject("404", "Nganh not found", null)));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseObject("500", "Internal Server Error", null));
//        }
//    }
//
//    @GetMapping("/countAllNganh")
//    public ResponseEntity<ResponseObject> countAllNganh() {
//        try {
//            long count = NganhService.countAllNganh();
//            return ResponseEntity.ok(new ResponseObject("200", "Count All Nganh", count));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseObject("500", "Internal Server Error", null));
//        }
//    }
//
//    @PostMapping("/addNewNganh")
//    public ResponseEntity<ResponseObject> addNewNganh(@RequestBody NganhModel NganhModel) {
//        try {
//            NganhModel savedNganh = NganhService.saveNganh(NganhModel);
//            return new ResponseEntity<>(new ResponseObject("200", "Successfully added", savedNganh), HttpStatus.CREATED);
//        } catch (BadResourceException | ResourceAlreadyExistsException e) {
//            return new ResponseEntity<>(new ResponseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/updateNganh")
//    public ResponseEntity<ResponseObject> updateNganh(@RequestBody NganhModel NganhModel) {
//        try {
//            NganhService.updateNganh(NganhModel);
//            return new ResponseEntity<>(new ResponseObject("200", "Successfully updated", NganhModel), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ResponseObject("400", e.getMessage(), null), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/deleteNganh/{id}")
//    public ResponseEntity<ResponseObject> deleteNganh(@PathVariable Long id) {
//        try {
//            NganhService.deleteByIdNganh(id);
//            return new ResponseEntity<>(new ResponseObject("200", "Successfully deleted", null), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ResponseObject("404", e.getMessage(), null), HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
