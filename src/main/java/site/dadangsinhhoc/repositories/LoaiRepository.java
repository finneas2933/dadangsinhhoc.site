package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.LoaiModel;

@Repository
public interface LoaiRepository extends JpaRepository<LoaiModel, Long> {
}
