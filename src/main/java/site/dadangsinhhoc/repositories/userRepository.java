package site.dadangsinhhoc.repositories;

import site.dadangsinhhoc.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    userModel findByEmail(String email);
    userModel findByUserName(String userName);
    userModel findByPhone(String phone);
}