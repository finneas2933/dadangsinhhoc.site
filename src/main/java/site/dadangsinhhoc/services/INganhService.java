package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NganhModel;

@Service
public interface INganhService {

     boolean existById(Long id);

     ResponseObject findById(Long id);

     ResponseObject getAllNganh();

     ResponseObject getAllNganhByLoai(Boolean loai);

     ResponseObject countAllNganh();

     ResponseObject saveNganh(NganhModel nganhModel);

     ResponseObject updateNganh(Long id, NganhModel nganhModel);

     ResponseObject deleteByIdNganh(Long id);

}
