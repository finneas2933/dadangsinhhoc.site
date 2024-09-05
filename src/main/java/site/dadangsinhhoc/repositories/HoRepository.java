package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.HoModel;

import java.util.List;

@Repository
public interface HoRepository extends JpaRepository<HoModel, Long> {
    List<HoModel> findAll(Specification<HoModel> spec);
}
