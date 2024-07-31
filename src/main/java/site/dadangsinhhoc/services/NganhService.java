package site.dadangsinhhoc.services;

import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.repositories.NganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NganhService {
    private final NganhRepository nganhRepository;

    @Autowired
    public NganhService(NganhRepository nganhRepository) {
        this.nganhRepository = nganhRepository;
    }

    public boolean existById(long id) {
        return nganhRepository.existsById(id);
    }

    public ResponseObject findById(long id) {
        Optional<NganhModel> dtvNganh = nganhRepository.findById(id);
        if (dtvNganh.isPresent()) {
            return ResponseObject.success(dtvNganh.get());
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
    }

    public ResponseObject getAllNganh() {
        List<NganhModel> nganhModels = nganhRepository.findAll();
        return ResponseObject.success(nganhModels);
    }

    public ResponseObject countAllNganh() {
        long quantity = nganhRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveNganh(NganhModel nganhModel) {
        if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (nganhRepository.existsById(nganhModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        NganhModel savedNganh = nganhRepository.save(nganhModel);
        return ResponseObject.success(savedNganh);
    }

    public ResponseObject updateNganh(NganhModel nganhModel) {
        if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_BO` is null or empty");
        }
        if (!nganhRepository.existsById(nganhModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Bo with id: " + nganhModel.getId());
        }
        return ResponseObject.success(nganhRepository.save(nganhModel));
    }

    public ResponseObject deleteByIdNganh(Long id) {
        if (!nganhRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            nganhRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }
}