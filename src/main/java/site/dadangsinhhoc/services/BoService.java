package site.dadangsinhhoc.services;

import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.BoModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.repositories.BoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoService {
    private final BoRepository boRepository;

    @Autowired
    public BoService(BoRepository boRepository) {
        this.boRepository = boRepository;
    }

    public boolean existById(long id) {
        return boRepository.existsById(id);
    }

    public ResponseObject findById(long id) {
        Optional<BoModel> dtvBo = boRepository.findById(id);
        if (dtvBo.isPresent()) {
            return ResponseObject.success(dtvBo.get());
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
    }

    public ResponseObject getAllBo() {
        List<BoModel> boModels = boRepository.findAll();
        return ResponseObject.success(boModels);
    }

    public ResponseObject countAllBo() {
        long quantity = boRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveBo(BoModel boModel) {
        if (boModel.getName() == null || boModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (boRepository.existsById(boModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        BoModel savedBo = boRepository.save(boModel);
        return ResponseObject.success(savedBo);
    }

    public ResponseObject updateBo(BoModel boModel) {
        if (boModel.getName() == null || boModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_BO` is null or empty");
        }
        if (!boRepository.existsById(boModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find BO with id: " + boModel.getId());
        }
        return ResponseObject.success(boRepository.save(boModel));
    }

    public ResponseObject deleteByIdBo(Long id) {
        if (!boRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            boRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
