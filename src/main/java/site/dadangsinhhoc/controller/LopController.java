package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.services.NganhService;

@RestController
@RequestMapping("/api/lop")
public class LopController {
    private final NganhService lopService;

    @Autowired
    public LopController(NganhService lopService) {
        this.lopService = lopService;
    }

    @GetMapping("/getAllNganh")
    public ResponseObject getAllNganh() {
        return lopService.getAllNganh();
    }

    @GetMapping("/getNganhById/{id}")
    public ResponseObject getNganhById(@PathVariable Long id) {
        return lopService.findById(id);
    }

    @GetMapping("/countAllNganh")
    public ResponseObject countAllNganh() {
        return lopService.countAllNganh();
    }

    @PostMapping("/addNewNganh")
    public ResponseObject addNewNganh(@RequestBody NganhModel NganhModel) {
        return lopService.saveNganh(NganhModel);
    }

    @PutMapping("/updateNganh/{id}")
    public ResponseObject updateNganh(@PathVariable Long id, @RequestBody NganhModel lopModel) {
        lopModel.setId(id);
        return lopService.updateNganh(id, lopModel);
    }

    @DeleteMapping("/deleteNganh/{id}")
    public ResponseObject deleteNganh(@PathVariable Long id) {
        return lopService.deleteByIdNganh(id);
    }

}
