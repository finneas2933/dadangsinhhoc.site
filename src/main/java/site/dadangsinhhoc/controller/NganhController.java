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
    public ResponseObject getNganhById(@PathVariable Integer id) {
        return nganhService.findNganhById(id);
    }

    @GetMapping("/countAllNganh")
    public ResponseObject countAllNganh() {
        return nganhService.countAllNganh();
    }

    @PostMapping("/addNewNganh")
    public ResponseObject addNewNganh(@RequestBody NganhModel nganhModel) {
        return nganhService.saveNganh(nganhModel);
    }

    @PutMapping("/updateNganh")
    public ResponseObject updateNganh(@RequestBody NganhModel nganhModel) {
        return nganhService.updateNganh(nganhModel);
    }

    @DeleteMapping("/deleteNganh/{id}")
    public ResponseObject deleteNganh(@PathVariable Integer id) {
        return nganhService.deleteByIdNganh(id);
    }

}
