package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;;
import org.springframework.data.jpa.repository.JpaRepository;
import site.dadangsinhhoc.models.HoModel;

@Repository
public interface HoRepository extends JpaRepository<HoModel, Long> {
}
