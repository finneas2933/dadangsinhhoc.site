package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.BoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import site.dadangsinhhoc.models.LopModel;

@Repository
public interface LopRepository extends JpaRepository<LopModel, Long> {
}
