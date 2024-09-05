package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.IntroduceModel;
import site.dadangsinhhoc.services.IntroduceService;

@RestController
@RequestMapping("/api/introduce")
public class IntroduceController {
    private final IntroduceService introduceService;

    @Autowired
    public IntroduceController(IntroduceService introduceService) {
        this.introduceService = introduceService;
    }

    @GetMapping("/getAllIntroduce")
    public ResponseObject getAllIntroduce() {
        return introduceService.getAllIntroduce();
    }

    @GetMapping("/countAllIntroduce")
    public ResponseObject countAllIntroduce() {
        return introduceService.countAllIntroduce();
    }

}
