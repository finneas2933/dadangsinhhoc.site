package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.services.LopService;

@RestController
@RequestMapping("/api/lop")
public class LopController {
    private final LopService lopService;

    @Autowired
    public LopController(LopService lopService) {
        this.lopService = lopService;
    }

    @GetMapping("/getAllLop")
    public ResponseObject getAllLop() {
        return lopService.getAllLop();
    }

    @GetMapping("/getLopById/{id}")
    public ResponseObject getLopById(@PathVariable Long id) {
        return lopService.findLopById(id);
    }

    @GetMapping("/countAllLop")
    public ResponseObject countAllLop() {
        return lopService.countAllLop();
    }

    @PostMapping("/addNewLop")
    public ResponseObject addNewLop(@RequestBody LopModel LopModel) {
        return lopService.saveLop(LopModel);
    }

    @PutMapping("/updateLop/{id}")
    public ResponseObject updateLop(@PathVariable Long id, @RequestBody LopModel lopModel) {
        lopModel.setId(id);
        return lopService.updateLop(id, lopModel);
    }

    @DeleteMapping("/deleteLop/{id}")
    public ResponseObject deleteLop(@PathVariable Long id) {
        return lopService.deleteByIdLop(id);
    }

}
