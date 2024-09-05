package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.BannerModel;
import site.dadangsinhhoc.services.BannerService;

@RestController
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/getAllBanner")
    public ResponseObject getAllBanner() {
        return bannerService.getAllBanner();
    }

    @GetMapping("/countAllBanner")
    public ResponseObject countAllBanner() {
        return bannerService.countAllBanner();
    }

    @PostMapping("/addNewBanner")
    public ResponseObject addNewBanner(@RequestBody BannerModel bannerModel) {
        return bannerService.saveBanner(bannerModel);
    }

    @DeleteMapping("/deleteBanner/{id}")
    public ResponseObject deleteBanner(@PathVariable Long id) {
        return bannerService.deleteByIdBanner(id);
    }

}
