package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.repositories.NganhRepository;
import org.springframework.data.jpa.domain.Specification;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NganhService {
    private final NganhRepository nganhRepository;

    @Autowired
    public NganhService(NganhRepository nganhRepository) {
        this.nganhRepository = nganhRepository;
    }

    public boolean existById(Long id) {
        return nganhRepository.existsById(id);
    }

    public ResponseObject findById(Long id) {
        return nganhRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllNganh() {
        try {
            List<NganhModel> nganhModels = nganhRepository.findAll();
            return ResponseObject.success(nganhModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Nganh");
        }
    }

    public ResponseObject getAllNganhByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<NganhModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<NganhModel> nganhModels = nganhRepository.findAll(spec);
            return ResponseObject.success(nganhModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Nganh by Loai");
        }
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
        nganhModel.setId(null);
        NganhModel savedNganh = nganhRepository.save(nganhModel);
        return ResponseObject.success(savedNganh);
    }

    public ResponseObject updateNganh(Long id, NganhModel nganhModel) {
        if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Nganh` is null or empty");
        }
        if (!nganhRepository.existsById(nganhModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Nganh with id: " + nganhModel.getId());
        }
        return nganhRepository.findById(id)
                .map(existingNganh -> {
                    existingNganh.setName(nganhModel.getName());
                    existingNganh.setNameLatinh(nganhModel.getNameLatinh());
                    existingNganh.setLoai(nganhModel.getLoai());
                    existingNganh.setStatus(nganhModel.getStatus());
                    existingNganh.setUpdatedAt(nganhModel.getUpdatedAt());
                    existingNganh.setUpdatedBy(nganhModel.getUpdatedBy());
                    return ResponseObject.success(nganhRepository.save(existingNganh));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Nganh with id: " + id));
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
