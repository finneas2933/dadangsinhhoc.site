package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.BannerModel;

@Service
public interface IBannerService {

    boolean existById(Long id);

    ResponseObject findById(Long id) throws Throwable;

    ResponseObject getAllBanner();

    ResponseObject countAllBanner();

    ResponseObject saveBanner(BannerModel bannerModel);

    ResponseObject updateBanner(Long id, BannerModel bannerModel);

    ResponseObject deleteByIdBanner(Long id);

}
