package site.dadangsinhhoc.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.LoaiDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LoaiModel;
import site.dadangsinhhoc.services.HelperService;
import site.dadangsinhhoc.repositories.LoaiRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoaiService {
    private final LoaiRepository loaiRepository;
    private final HelperService helperService;

    @Autowired
    public LoaiService(LoaiRepository loaiRepository, HelperService helperService) {
        this.loaiRepository = loaiRepository;
        this.helperService = helperService;
    }

    public boolean existById(Long id) {
        return loaiRepository.existsById(id);
    }

    public ResponseObject findById(Long id) {
        return loaiRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public ResponseObject getAllLoai() {
        try {
            List<LoaiModel> loaiModels = loaiRepository.findAll();
            return ResponseObject.success(loaiModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Loai");
        }
    }

    public ResponseObject getAllLoaiByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<LoaiModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<LoaiModel> loaiModels = loaiRepository.findAll(spec);
            return ResponseObject.success(loaiModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Loai by Loai");
        }
    }

    public ResponseObject countAllLoai() {
        long quantity = loaiRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveLoai(LoaiDTO loaiDTO, BindingResult result) {
        try {
            if (loaiDTO.getName() == null || loaiDTO.getName().isEmpty()) {
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
            }
//            if (loaiRepository.existsById(loaiModel.getId())) {
//                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
//            }
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", "));
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), errorMessage);
            }
            LoaiModel loaiModel = new LoaiModel();
            loaiModel.setName(loaiDTO.getName());
            loaiModel.setNameLatinh(loaiDTO.getNameLatinh());
            loaiModel.setLoai(loaiDTO.getLoai());
            loaiModel.setHo(loaiDTO.getHo());
            loaiModel.setRPH(loaiDTO.getRPH());
            loaiModel.setDacDiem(loaiDTO.getDacDiem());
            loaiModel.setGiaTriSuDung(loaiDTO.getGiaTriSuDung());
            loaiModel.setPhanBo(loaiDTO.getPhanBo());
            loaiModel.setNguonTaiLieu(loaiDTO.getNguonTaiLieu());

            MultipartFile file = loaiDTO.getHinhAnh();
            if(file != null && !file.isEmpty()) {
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseObject.error(ErrorCode.PAYLOAD_TOO_LARGE.getCode(), ErrorCode.PAYLOAD_TOO_LARGE.getMessage());
                }
                if (file.getContentType() != null && file.getContentType().contains("image")) {
                    return ResponseObject.error(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode(), ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage());
                }
                String fileName = helperService.StoreFile(file);
                File tempFile = new File(fileName); // Assuming fileName is the path to the file
                byte[] fileBytes = Files.readAllBytes(tempFile.toPath());
                MultipartFile multipartFile = new MockMultipartFile("file", tempFile.getName(), file.getContentType(), fileBytes);
                loaiModel.setHinhAnh(multipartFile);
            }

            loaiModel.setId(null);
            LoaiModel savedLoai = loaiRepository.save(loaiModel);
            return ResponseObject.success(savedLoai);
        } catch (Exception e) {
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }

    }



    public ResponseObject updateLoai(Long id, LoaiModel loaiModel) {
        if (loaiModel.getName() == null || loaiModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Loai` is null or empty");
        }
        if (!loaiRepository.existsById(loaiModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Loai with id: " + loaiModel.getId());
        }
        return loaiRepository.findById(id)
                .map(existingLoai -> {
                    existingLoai.setName(loaiModel.getName());
                    existingLoai.setNameLatinh(loaiModel.getNameLatinh());
                    existingLoai.setLoai(loaiModel.getLoai());
                    existingLoai.setHo(loaiModel.getHo());
                    existingLoai.setRPH(loaiModel.getRPH());
                    existingLoai.setDacDiem(loaiModel.getDacDiem());
                    existingLoai.setGiaTriSuDung(loaiModel.getGiaTriSuDung());
                    existingLoai.setPhanBo(loaiModel.getPhanBo());
                    existingLoai.setNguonTaiLieu(loaiModel.getNguonTaiLieu());
                    existingLoai.setHinhAnh(loaiModel.getHinhAnh());
                    existingLoai.setMucDoBaoTonIUCN(loaiModel.getMucDoBaoTonIUCN());
                    existingLoai.setMucDoBaoTonSDVN(loaiModel.getMucDoBaoTonSDVN());
                    existingLoai.setMucDoBaoTonNDCP(loaiModel.getMucDoBaoTonNDCP());
                    existingLoai.setMucDoBaoTonND64CP(loaiModel.getMucDoBaoTonND64CP());
                    existingLoai.setStatus(loaiModel.getStatus());
                    existingLoai.setUpdatedAt(loaiModel.getUpdatedAt());
                    existingLoai.setUpdatedBy(loaiModel.getUpdatedBy());
                    return ResponseObject.success(loaiRepository.save(existingLoai));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Loai with id: " + id));
    }

    public ResponseObject deleteByIdLoai(Long id) {
        if (!loaiRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            loaiRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
