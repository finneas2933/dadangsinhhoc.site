package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
    UserModel findByName(String userName);
    UserModel findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByName(String userName);
    boolean existsByPhone(String phone);
}