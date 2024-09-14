package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.services.HoService;

@RestController
@RequestMapping("/api/Ho")
public class HoController {
    private final HoService HoService;

    @Autowired
    public HoController(HoService HoService) {
        this.HoService = HoService;
    }

    @GetMapping("/getAllHo")
    public ResponseObject getAllHo() {
        return HoService.getAllHo();
    }

    @GetMapping("/getAllHoByLoai/{loai}")
    public ResponseObject getAllHoByLoai(@PathVariable Boolean loai) {
        return HoService.getAllHoByLoai(loai);
    }

    @GetMapping("/searchHo")
    public ResponseObject searchByNameOrNameLatinh(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "loai", required = false) Boolean loai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return HoService.searchByNameOrNameLatinh(keyword, loai, page, size);
    }

    @GetMapping("/checkDuplicateNameLatinh")
    public ResponseObject checkDuplicateNameLatinh(
            @RequestParam("nameLatinh") String nameLatinh,
            @RequestParam("isAddingNew") boolean isAddingNew,
            @RequestParam(value = "oldNameLatinh", required = false) String oldNameLatinh) {
        boolean isDuplicate = HoService.checkDuplicateNameLatinh(nameLatinh, isAddingNew, oldNameLatinh);
        return ResponseObject.success(!isDuplicate); // Trả về true nếu không trùng, false nếu trùng
    }

        @GetMapping("/getHoById/{id}")
        public ResponseObject getHoById (@PathVariable Long id){
            return HoService.findById(id);
        }

        @GetMapping("/countAllHo")
        public ResponseObject countAllHo () {
            return HoService.countAllHo();
        }

        @PostMapping("/addNewHo")
        public ResponseObject addNewHo (@RequestBody HoModel HoModel){
            return HoService.saveHo(HoModel);
        }

        @PutMapping("/updateHo/{id}")
        public ResponseObject updateHo (@PathVariable Long id, @RequestBody HoModel HoModel){
            HoModel.setId(id);
            return HoService.updateHo(id, HoModel);
        }

        @DeleteMapping("/deleteHo/{id}")
        public ResponseObject deleteHo (@PathVariable Long id){
            return HoService.deleteByIdHo(id);
        }

    }
