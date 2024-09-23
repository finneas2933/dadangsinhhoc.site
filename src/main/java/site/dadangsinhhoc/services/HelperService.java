package site.dadangsinhhoc.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.SearchCriteriaDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.*;
import site.dadangsinhhoc.repositories.LoaiRepository;
import site.dadangsinhhoc.repositories.NganhRepository;
import site.dadangsinhhoc.repositories.LopRepository;
import site.dadangsinhhoc.repositories.BoRepository;
import site.dadangsinhhoc.repositories.HoRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HelperService {

    private final LoaiRepository loaiRepository;

    @Autowired
    public HelperService(LoaiRepository loaiRepository, NganhRepository nganhRepository,
                         LopRepository lopRepository, BoRepository boRepository, HoRepository hoRepository,
                         EntityManager entityManager) {
        this.loaiRepository = loaiRepository;
    }

    public ResponseObject searchLoai(@NotNull SearchCriteriaDTO criteria) {
        Specification<LoaiModel> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

        Join<LoaiModel, HoModel> hoJoin = root.join("ho", JoinType.INNER);
        Join<HoModel, BoModel> boJoin = hoJoin.join("bo", JoinType.INNER);
        Join<BoModel, LopModel> lopJoin = boJoin.join("lop", JoinType.INNER);
        Join<LopModel, NganhModel> nganhJoin = lopJoin.join("nganh", JoinType.INNER);

        if (criteria.getNganhId() != null) {
            predicates.add(criteriaBuilder.equal(nganhJoin.get("id"), criteria.getNganhId()));
        }
        if (criteria.getLopId() != null) {
            predicates.add(criteriaBuilder.equal(lopJoin.get("id"), criteria.getLopId()));
        }
        if (criteria.getBoId() != null) {
            predicates.add(criteriaBuilder.equal(boJoin.get("id"), criteria.getBoId()));
        }
        if (criteria.getHoId() != null) {
            predicates.add(criteriaBuilder.equal(hoJoin.get("id"), criteria.getHoId()));
        }
        if (criteria.getMucDoBaoTonIUCN() != null) {
            predicates.add(criteriaBuilder.equal(root.get("mucDoBaoTonIUCN"), criteria.getMucDoBaoTonIUCN()));
        }
        if (criteria.getMucDoBaoTonSDVN() != null) {
            predicates.add(criteriaBuilder.equal(root.get("mucDoBaoTonSDVN"), criteria.getMucDoBaoTonSDVN()));
        }
        if (criteria.getMucDoBaoTonNDCP() != null) {
            predicates.add(criteriaBuilder.equal(root.get("mucDoBaoTonNDCP"), criteria.getMucDoBaoTonNDCP()));
        }
        if (criteria.getMucDoBaoTonND64CP() != null) {
            predicates.add(criteriaBuilder.equal(root.get("mucDoBaoTonND64CP"), criteria.getMucDoBaoTonND64CP()));
        }
        if (criteria.getRPH() != null) {
            predicates.add(criteriaBuilder.equal(root.get("rPH"), criteria.getRPH()));
        }
        if (criteria.getTenTiengViet() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criteria.getTenTiengViet().toLowerCase() + "%"));
        }
        if (criteria.getTenKhoaHoc() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nameLatinh")), "%" + criteria.getTenKhoaHoc().toLowerCase() + "%"));
        }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        System.out.println("Search Criteria: " + criteria);

        StringBuilder sqlBuilder = new StringBuilder("SELECT l FROM LoaiModel l ");
        sqlBuilder.append("JOIN l.ho h ");
        sqlBuilder.append("JOIN h.bo b ");
        sqlBuilder.append("JOIN b.lop lp ");
        sqlBuilder.append("JOIN lp.nganh n ");
        sqlBuilder.append("WHERE 1=1 ");

        if (criteria.getNganhId() != null) sqlBuilder.append("AND n.id = :nganhId ");
        if (criteria.getLopId() != null) sqlBuilder.append("AND lp.id = :lopId ");
        if (criteria.getBoId() != null) sqlBuilder.append("AND b.id = :boId ");
        if (criteria.getHoId() != null) sqlBuilder.append("AND h.id = :hoId ");
        if (criteria.getMucDoBaoTonIUCN() != null) sqlBuilder.append("AND l.mucDoBaoTonIUCN = :mucDoBaoTonIUCN ");
        if (criteria.getMucDoBaoTonSDVN() != null) sqlBuilder.append("AND l.mucDoBaoTonSDVN = :mucDoBaoTonSDVN ");
        if (criteria.getMucDoBaoTonNDCP() != null) sqlBuilder.append("AND l.mucDoBaoTonNDCP = :mucDoBaoTonNDCP ");
        if (criteria.getMucDoBaoTonND64CP() != null) sqlBuilder.append("AND l.mucDoBaoTonND64CP = :mucDoBaoTonND64CP ");
        if (criteria.getRPH() != null) sqlBuilder.append("AND l.rPH = :rPH ");
        if (criteria.getTenTiengViet() != null) sqlBuilder.append("AND LOWER(l.name) LIKE LOWER(:tenTiengViet) ");
        if (criteria.getTenKhoaHoc() != null) sqlBuilder.append("AND LOWER(l.nameLatinh) LIKE LOWER(:tenKhoaHoc) ");

        System.out.println("Generated SQL: " + sqlBuilder.toString());

        List<LoaiModel> results = loaiRepository.findAll(spec);
        return ResponseObject.success(results);
    }

    public String StoreFile(@NotNull MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = fileName + "_" + UUID.randomUUID().toString() ;
        Path uploadPath = Paths.get("media");
        if(!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
        Path destination = Paths.get(uploadPath.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}