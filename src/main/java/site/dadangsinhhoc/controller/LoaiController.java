package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LoaiModel;
import site.dadangsinhhoc.services.LoaiService;

@RestController
@RequestMapping("/api/loai")
public class LoaiController {
    private final LoaiService loaiService;

    @Autowired
    public LoaiController(LoaiService loaiService) {
        this.loaiService = loaiService;
    }

    @GetMapping("/getAllLoai")
    public ResponseObject getAllLoai() {
        return loaiService.getAllLoai();
    }

    @GetMapping("/getAllLoaiByLoai/{loai}")
    public ResponseObject getAllLoaiByLoai(@PathVariable Boolean loai) {
        return loaiService.getAllLoaiByLoai(loai);
    }

    @GetMapping("/getLoaiById/{id}")
    public ResponseObject getLoaiById(@PathVariable Long id) {
        return loaiService.findById(id);
    }

    @GetMapping("/countAllLoai")
    public ResponseObject countAllLoai() {
        return loaiService.countAllLoai();
    }

    @PostMapping("/addNewLoai")
    public ResponseObject addNewLoai(@RequestBody LoaiModel LoaiModel) {
        return loaiService.saveLoai(LoaiModel);
    }

    @PutMapping("/updateLoai/{id}")
    public ResponseObject updateLoai(@PathVariable Long id, @RequestBody LoaiModel loaiModel) {
        loaiModel.setId(id);
        return loaiService.updateLoai(id, loaiModel);
    }

    @DeleteMapping("/deleteLoai/{id}")
    public ResponseObject deleteLoai(@PathVariable Long id) {
        return loaiService.deleteByIdLoai(id);
    }

}
