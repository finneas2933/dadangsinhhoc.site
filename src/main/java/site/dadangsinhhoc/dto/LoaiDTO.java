package site.dadangsinhhoc.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.models.HoModel;

@Data
public class LoaiDTO {
    private String name;
    private String nameLatinh;
    private Boolean loai;
    private HoModel ho;
    private Boolean RPH;
    private String dacDiem;
    private String giaTriSuDung;
    private String phanBo;
    private String nguonTaiLieu;
    private MultipartFile hinhAnh;
}
