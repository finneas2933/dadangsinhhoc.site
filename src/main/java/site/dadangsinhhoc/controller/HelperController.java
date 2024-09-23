package site.dadangsinhhoc.controller;

import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.SearchCriteriaDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.services.HelperService;

@RestController
@RequestMapping("/api/helper")
public class HelperController {

    private final HelperService helperService;

    public HelperController(HelperService helperService) {
        this.helperService = helperService;
    }

    @PostMapping("/search")
    public ResponseObject search(@RequestBody SearchCriteriaDTO criteria) {
        return helperService.searchLoai(criteria);
    }
}