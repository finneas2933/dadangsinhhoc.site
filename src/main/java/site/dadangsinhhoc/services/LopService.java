package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.repositories.LopRepository;

import java.util.List;
import java.util.Optional;

public class LopService {
    private final LopRepository lopRepository;

    @Autowired
    public LopService(LopRepository lopRepository) {
        this.lopRepository = lopRepository;
    }

    public ResponseObject findById(long id) {
        Optional<LopModel> dtvLop = lopRepository.findById(id);
        if (dtvLop.isPresent()) {
            return ResponseObject.success(dtvLop.get());
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
    }

    public ResponseObject saveLop(LopModel lopModel) {
        if (lopModel.getName() == null || lopModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (lopRepository.existsById(lopModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        LopModel savedLop = lopRepository.save(lopModel);
        return ResponseObject.success(savedLop);
    }

    public ResponseObject updateLop(LopModel lopModel) {
        if (lopModel.getName() == null || lopModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_LOP` is null or empty");
        }
        if (!lopRepository.existsById(lopModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find LOP with id: " + lopModel.getId());
        }
        return ResponseObject.success(lopRepository.save(lopModel));
    }

    public ResponseObject deleteByIdLop(Long id) {
        if (!lopRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            lopRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

    public ResponseObject getAllLop() {
        List<LopModel> lopModels = lopRepository.findAll();
        return ResponseObject.success(lopModels);
    }

    public ResponseObject countAllLop() {
        long quantity = lopRepository.count();
        return ResponseObject.success(quantity);
    }
}
