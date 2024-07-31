package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.models.ResponseObject;
import site.dadangsinhhoc.repositories.HoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HoService {
    private final HoRepository hoRepository;

    @Autowired
    public HoService(HoRepository hoRepository) {
        this.hoRepository = hoRepository;
    }

    public boolean existById(long id) {
        return hoRepository.existsById(id);
    }

    public ResponseObject getAllHo() {
        List<HoModel> hoModels = hoRepository.findAll();
        return ResponseObject.success(hoModels);
    }

    public ResponseObject findById(long id) {
        Optional<HoModel> dtvHo = hoRepository.findById(id);
        if (dtvHo.isPresent()) {
            return ResponseObject.success(dtvHo.get());
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
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
        HoModel savedHo = hoRepository.save(hoModel);
        return ResponseObject.success(savedHo);
    }

    public ResponseObject updateHo(HoModel hoModel) {
        if (hoModel.getName() == null || hoModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_HO` is null or empty");
        }
        if (!hoRepository.existsById(hoModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find HO with id: " + hoModel.getId());
        }
        return ResponseObject.success(hoRepository.save(hoModel));
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
