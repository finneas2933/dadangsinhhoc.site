package site.dadangsinhhoc.repositories;

import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.models.UserModel;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Integer> {
    List<TokenModel> findByUser(UserModel user);
    TokenModel findByToken(String token);
    TokenModel findByRefreshToken(String token);
}