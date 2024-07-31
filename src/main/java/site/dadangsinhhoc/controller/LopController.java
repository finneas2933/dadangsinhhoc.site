package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.services.LopService;

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
    public ResponseObject getLopById(@PathVariable long id) {
        return lopService.findById(id);
    }

    @GetMapping("/countAllLop")
    public ResponseObject countAllLop() {
        return lopService.countAllLop();
    }

    @PostMapping("/addNewLop")
    public ResponseObject addNewLop(@RequestBody LopModel LopModel) {
        return lopService.saveLop(LopModel);
    }

    @PutMapping("/updateLop")
    public ResponseObject updateLop(@RequestBody LopModel lopModel) {
        return lopService.updateLop(lopModel);
    }

    @DeleteMapping("/deleteLop/{id}")
    public ResponseObject deleteLop(@PathVariable Long id) {
        return lopService.deleteByIdLop(id);
    }
}
