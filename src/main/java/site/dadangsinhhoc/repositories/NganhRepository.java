package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.BoModel;
import site.dadangsinhhoc.models.NganhModel;

import java.util.UUID;

@Repository
public interface NganhRepository extends JpaRepository<NganhModel, Long> {
}
