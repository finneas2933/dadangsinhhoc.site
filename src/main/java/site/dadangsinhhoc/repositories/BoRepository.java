package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.BoModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BoRepository extends JpaRepository <BoModel, Long> {
}
