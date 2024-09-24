package site.dadangsinhhoc.services;

import com.nimbusds.jose.JOSEException;
import site.dadangsinhhoc.models.UserModel;

import java.text.ParseException;

public interface ITokenService {
    UserModel validateAndGetUserFromToken(String token) throws JOSEException, ParseException;
    String generateToken(UserModel userModel);
}