package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.repositories.HoRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class HoService {
    private final HoRepository hoRepository;

    @Autowired
    public HoService(HoRepository hoRepository) {
        this.hoRepository = hoRepository;
    }

    public boolean existById(Long id) {
        return hoRepository.existsById(id);
    }

    public ResponseObject findById(Long id) {
        return hoRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllHo() {
        try {
            List<HoModel> hoModels = hoRepository.findAll();
            return ResponseObject.success(hoModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Ho");
        }
    }

    public ResponseObject getAllHoByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<HoModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<HoModel> hoModels = hoRepository.findAll(spec);
            return ResponseObject.success(hoModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Ho by Loai");
        }
    }

    public ResponseObject countAllHo() {
        long quantity = hoRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveHo(HoModel hoModel) {
        if (hoModel.getName() == null || hoModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (hoRepository.existsById(hoModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        hoModel.setId(null);
        HoModel savedHo = hoRepository.save(hoModel);
        return ResponseObject.success(savedHo);
    }

    public ResponseObject updateHo(Long id, HoModel hoModel) {
        if (hoModel.getName() == null || hoModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Ho` is null or empty");
        }
        if (!hoRepository.existsById(hoModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Ho with id: " + hoModel.getId());
        }
        return hoRepository.findById(id)
                .map(existingHo -> {
                    existingHo.setName(hoModel.getName());
                    existingHo.setNameLatinh(hoModel.getNameLatinh());
                    existingHo.setLoai(hoModel.getLoai());
                    existingHo.setIdBo(hoModel.getIdBo());
                    existingHo.setStatus(hoModel.getStatus());
                    existingHo.setUpdatedAt(hoModel.getUpdatedAt());
                    existingHo.setUpdatedBy(hoModel.getUpdatedBy());
                    return ResponseObject.success(hoRepository.save(existingHo));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Ho with id: " + id));
    }

    public ResponseObject deleteByIdHo(Long id) {
        if (!hoRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            hoRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
