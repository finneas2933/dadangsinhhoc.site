package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    userModel findByEmail(String email);
    userModel findByUserName(String userName);
    userModel findByPhone(String phone);
}