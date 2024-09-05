package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.services.NganhService;

@RestController
@RequestMapping("/api/nganh")
public class NganhController {
    private final NganhService nganhService;

    @Autowired
    public NganhController(NganhService nganhService) {
        this.nganhService = nganhService;
    }

    @GetMapping("/getAllNganh")
    public ResponseObject getAllNganh() {
        return nganhService.getAllNganh();
    }

    @GetMapping("/getNganhById/{id}")
    public ResponseObject getNganhById(@PathVariable Long id) {
        return nganhService.findById(id);
    }

    @GetMapping("/countAllNganh")
    public ResponseObject countAllNganh() {
        return nganhService.countAllNganh();
    }

    @PostMapping("/addNewNganh")
    public ResponseObject addNewNganh(@RequestBody NganhModel NganhModel) {
        return nganhService.saveNganh(NganhModel);
    }

    @PutMapping("/updateNganh/{id}")
    public ResponseObject updateNganh(@PathVariable Long id, @RequestBody NganhModel nganhModel) {
        nganhModel.setId(id);
        return nganhService.updateNganh(id, nganhModel);
    }

    @DeleteMapping("/deleteNganh/{id}")
    public ResponseObject deleteNganh(@PathVariable Long id) {
        return nganhService.deleteByIdNganh(id);
    }

}
