package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface userRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
    UserModel findByUserName(String userName);
    UserModel findByPhone(String phone);
}