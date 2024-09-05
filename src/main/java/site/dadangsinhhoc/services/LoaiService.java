package site.dadangsinhhoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LoaiModel;
import site.dadangsinhhoc.repositories.LoaiRepository;

import java.util.List;

@Service
public class LoaiService {
    private final LoaiRepository loaiRepository;

    @Autowired
    public LoaiService(LoaiRepository loaiRepository) {
        this.loaiRepository = loaiRepository;
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

    public ResponseObject countAllLoai() {
        long quantity = loaiRepository.count();
        return ResponseObject.success(quantity);
    }

    public ResponseObject saveLoai(LoaiModel loaiModel) {
        if (loaiModel.getName() == null || loaiModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (loaiRepository.existsById(loaiModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        LoaiModel savedLoai = loaiRepository.save(loaiModel);
        return ResponseObject.success(savedLoai);
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
                    existingLoai.setIdHo(loaiModel.getIdHo());
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
