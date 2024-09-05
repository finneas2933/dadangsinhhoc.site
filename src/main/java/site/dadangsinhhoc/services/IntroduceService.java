package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.IntroduceModel;
import site.dadangsinhhoc.repositories.IntroduceRepository;

import java.util.List;

@Service
public class IntroduceService {
    private final IntroduceRepository introduceRepository;

    @Autowired
    public IntroduceService(IntroduceRepository introduceRepository) {
        this.introduceRepository = introduceRepository;
    }

    public boolean existById(Long id) {
        return introduceRepository.existsById(id);
    }

    public ResponseObject findById(Long id) {
        return introduceRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllIntroduce() {
        try {
            List<IntroduceModel> introduceModels = introduceRepository.findAll();
            return ResponseObject.success(introduceModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Introduce");
        }
    }

    public ResponseObject countAllIntroduce() {
        long quantity = introduceRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveIntroduce(IntroduceModel introduceModel) {
        if (introduceRepository.existsById(introduceModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        IntroduceModel savedIntroduce = introduceRepository.save(introduceModel);
        return ResponseObject.success(savedIntroduce);
    }

    public ResponseObject updateIntroduce(Long id, IntroduceModel introduceModel) {
        if (!introduceRepository.existsById(introduceModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Introduce with id: " + introduceModel.getId());
        }
        return introduceRepository.findById(id)
                .map(existingIntroduce -> {
                    existingIntroduce.setContent(introduceModel.getContent());
                    existingIntroduce.setUpdatedAt(introduceModel.getUpdatedAt());
                    existingIntroduce.setUpdatedBy(introduceModel.getUpdatedBy());
                    return ResponseObject.success(introduceRepository.save(existingIntroduce));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Introduce with id: " + id));
    }

    public ResponseObject deleteByIdIntroduce(Long id) {
        if (!introduceRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            introduceRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
