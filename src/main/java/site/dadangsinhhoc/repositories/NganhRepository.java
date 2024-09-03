package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import site.dadangsinhhoc.models.NganhModel;


@Repository
public interface NganhRepository extends JpaRepository<NganhModel, Long> {
}
