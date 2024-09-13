package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.HoModel;

import java.util.List;

@Repository
public interface HoRepository extends JpaRepository<HoModel, Long> {
    List<HoModel> findAll(Specification<HoModel> spec);

    @Query("SELECT h FROM HoModel h WHERE (:keyword IS NULL OR h.name LIKE %:keyword% OR h.nameLatinh LIKE %:keyword%) AND (:loai IS NULL OR h.loai = :loai)")
    List<HoModel> searchByNameOrNameLatinh(@Param("keyword") String keyword, @Param("loai") Boolean loai);
//    @Query("SELECT h FROM HoModel h WHERE h.name LIKE %:keyword% OR h.nameLatinh LIKE %:keyword%")
//    List<HoModel> searchByNameOrNameLatinh(@Param("keyword") String keyword);
}
