package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.repositories.LopRepository;

import java.util.List;

@Service
public class LopService {
    private final LopRepository lopRepository;

    @Autowired
    public LopService(LopRepository lopRepository) {
        this.lopRepository = lopRepository;
    }

    public boolean existById(Long id) {
        return lopRepository.existsById(id);
    }

    public ResponseObject findLopById(Long id) {
        return lopRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllLop() {
        List<LopModel> lopModels = lopRepository.findAll();
        return ResponseObject.success(lopModels);
    }

    public ResponseObject countAllLop() {
        long quantity = lopRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveLop(LopModel lopModels) {
        if (lopModels.getName() == null || lopModels.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (lopRepository.existsById(lopModels.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        lopModels.setId(null);
        LopModel savedLop = lopRepository.save(lopModels);
        return ResponseObject.success(savedLop);
    }

    public ResponseObject updateLop(Long id, LopModel lopModel) {
        if (lopModel.getName() == null || lopModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Lop` is null or empty");
        }
        if (!lopRepository.existsById(lopModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Lop with id: " + lopModel.getId());
        }
        return lopRepository.findById(id)
                .map(existingNganh -> {
                    existingNganh.setName(lopModel.getName());
                    existingNganh.setNameLatinh(lopModel.getNameLatinh());
                    existingNganh.setLoai(lopModel.getLoai());
                    existingNganh.setStatus(lopModel.getStatus());
                    existingNganh.setUpdatedAt(lopModel.getUpdatedAt());
                    existingNganh.setUpdatedBy(lopModel.getUpdatedBy());
                    return ResponseObject.success(lopRepository.save(existingNganh));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Lop with id: " + id));
    }

    public ResponseObject deleteByIdLop(Long id) {
        if (!lopRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            lopRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
