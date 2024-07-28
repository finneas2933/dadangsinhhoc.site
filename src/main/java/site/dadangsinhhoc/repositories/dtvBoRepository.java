package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.boModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface dtvBoRepository extends JpaRepository <boModel, Long> {
}
