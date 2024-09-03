package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.services.HoService;

@RestController
public class HoController {
    private final HoService hoService;

    @Autowired
    public HoController(HoService hoService) {
        this.hoService = hoService;
    }

    @GetMapping("/getAllHo")
    public ResponseObject getAllHo(){
        return hoService.getAllHo();
    }

    @GetMapping("/getHoById/{id}")
    public ResponseObject getHoById(@PathVariable long id){
        return hoService.findById(id);
    }

    @GetMapping("/countAllHo")
    public ResponseObject countAllHo(){
        return hoService.countAllHo();
    }

    @PostMapping("/addNewHo")
    public ResponseObject addNewHo(@RequestBody HoModel HoModel){
        return hoService.saveHo(HoModel);
    }

    @PutMapping("/updateHo")
    public ResponseObject updateHo(@RequestBody HoModel hoModel){
        return hoService.updateHo(hoModel);
    }

    @DeleteMapping("/deleteHo/{id}")
    public ResponseObject deleteHo(@PathVariable Long id){
        return hoService.deleteByIdHo(id);
    }
}
