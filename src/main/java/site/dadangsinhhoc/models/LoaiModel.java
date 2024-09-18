package site.dadangsinhhoc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Entity
@Table(name = "loai")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idHo")
    private HoModel ho;

    @Column(name = "name")
    private String name;

    @Column(name = "nameLatinh", nullable = false)
    private String nameLatinh;

    @Column(name = "loai", nullable = false)
    private Boolean loai;

    // @Column(name = "idHo", nullable = false)
    // private Integer idHo;

    @Column(name = "rPH", nullable = false)
    private Boolean rPH;

    @Column(name = "dacDiem", columnDefinition = "TEXT")
    private String dacDiem;

    @Column(name = "giaTriSuDung")
    private String giaTriSuDung;

    @Column(name = "phanBo")
    private String phanBo;

    @Column(name = "nguonTaiLieu")
    private String nguonTaiLieu;

    @Column(name = "hinhAnh")
    private MultipartFile hinhAnh;

    @Column(name = "mucDoBaoTonIUCN", length = 100)
    private String mucDoBaoTonIUCN;

    @Column(name = "mucDoBaoTonSDVN", length = 100)
    private String mucDoBaoTonSDVN;

    @Column(name = "mucDoBaoTonNDCP", length = 100)
    private String mucDoBaoTonNDCP;

    @Column(name = "mucDoBaoTonND64CP", length = 100)
    private String mucDoBaoTonND64CP;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "updatedBy")
    private String updatedBy;

}
