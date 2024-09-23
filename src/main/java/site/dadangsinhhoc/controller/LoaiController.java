package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LoaiModel;
import site.dadangsinhhoc.services.LoaiService;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/loai")
public class LoaiController {
    private final LoaiService loaiService;

    @Autowired
    public LoaiController(LoaiService loaiService) {
        this.loaiService = loaiService;
    }

    @GetMapping("/getAll")
    public ResponseObject getLoai() {
        return loaiService.getAllLoai();
    }

    @GetMapping("/getAllLByLoai/{loai}")
    public ResponseObject getAllByLoai(@PathVariable Boolean loai) {
        return loaiService.getAllLoaiByLoai(loai);
    }

    @GetMapping("/getById/{id}")
    public ResponseObject getById(@PathVariable Long id) {
        return loaiService.findById(id);
    }

    @GetMapping("/countAllLoai")
    public ResponseObject countAll() {
        return loaiService.countAllLoai();
    }

    @PostMapping(value="/addNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseObject addNew(
            @ModelAttribute LoaiModel model,
            @RequestParam("thumbnails") MultipartFile thumbnail,
            @RequestParam("images") List<MultipartFile> image
            ) {
        return loaiService.saveLoai(model, thumbnail, image);
    }

    @PostMapping(value="/updateImage/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseObject updateImage(
            @RequestParam Long id,
            @ModelAttribute("file") List<MultipartFile> multipartFile
    ) {
        return loaiService.saveImageLoai(id, multipartFile);
    }

    @PutMapping("/update/{id}")
    public ResponseObject update(@PathVariable Long id, @RequestBody LoaiModel loaiModel) {
        loaiModel.setId(id);
        return loaiService.updateLoai(id, loaiModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject delete(@PathVariable Long id) {
        return loaiService.deleteByIdLoai(id);
    }

}
