//package site.dadangsinhhoc.controller;
//
//import site.dadangsinhhoc.exception.ErrorCode;
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
//            return ResponseObject.success( NganhModel);
//        } catch (Exception e) {
//            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
//        }
//    }
//
//    @GetMapping("/getNganhById/{id}")
//    public ResponseObject getNganhById(@PathVariable long id) {
//            Optional<NganhModel> dtvNganh = NganhService.findById(id);
//        return ResponseObject.success(dtvNganh);
//    }
//
//    @GetMapping("/countAllNganh")
//    public ResponseObject countAllNganh() {
//            long count = NganhService.countAllNganh();
//            return ResponseObject.success("Count All Nganh", count);
//    }
//
//    @PostMapping("/addNewNganh")
//    public ResponseObject addNewNganh(@RequestBody NganhModel NganhModel) {
//        NganhModel savedNganh = NganhService.saveNganh(NganhModel);
//        return ResponseObject.success(savedNganh);
//    }
//
//    @PutMapping("/updateNganh")
//    public ResponseObject updateNganh(@RequestBody NganhModel NganhModel) {
//        NganhService.updateNganh(NganhModel);
//        return ResponseObject.success(NganhModel);
//    }
//
//    @DeleteMapping("/deleteNganh/{id}")
//    public ResponseObject deleteNganh(@PathVariable Long id) {
//        NganhService.deleteByIdNganh(id);
//        return ResponseObject.success("Successfully deleted", null);
//    }
//}
