package site.dadangsinhhoc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.dto.request.AuthenticateRequest;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(TokenService tokenService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseObject findById(Integer id) {
        var authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}; Role: {}", authenticatedUser.getName(), authenticatedUser.getAuthorities());
        Optional<UserModel> user = userRepository.findById(id);
        return ResponseObject.success(user);
    }

    public ResponseObject findByUserName(String username) {
        UserModel user = userRepository.findByUserName(username);
        return ResponseObject.success(user);
    }

    public ResponseObject checkUserExists(String identifier) {
        boolean exists = userRepository.existsByUserName(identifier) ||
                userRepository.existsByEmail(identifier) ||
                userRepository.existsByPhone(identifier);

        if (exists) {
            return ResponseObject.success("User exists", true);
        } else {
            return ResponseObject.success("User does not exist", false);
        }
    }

    public ResponseObject createNewUser(UserModel user) {
        try {
            if (userRepository.existsByUserName(user.getUserName())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), "Username already exists");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), "Email already exists");
            }
            if (userRepository.existsByPhone(user.getPhone())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), "Phone number already exists");
            }

            // Set createdAt and updatedAt
            LocalDateTime now = LocalDateTime.now();
            user.setCreatedAt(now);
            user.setUpdatedAt(now);

            if (user.getRole()==null) {
                user.setRole("ROLE_USER");
            }

            UserModel savedUser = UserModel.builder()
                    .userName(user.getUserName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .city(user.getCity())
                    .district(user.getDistrict())
                    .avatar(user.getAvatar())
                    .gender(user.getGender())
                    .dob(user.getDob())
                    .address(user.getAddress())
                    .createdAt(now)
                    .updatedAt(now)
                    .lastSigninedTime(user.getLastSigninedTime())
                    .isAdmin(user.getIsAdmin())
                    .status(user.getStatus())
                    .secretKey(user.getSecretKey())
                    .role(user.getRole())
                    .build();
            UserModel savedUserModel = userRepository.save(savedUser);

            return ResponseObject.success("User created successfully", savedUserModel);
        } catch (Exception e) {
            // Log the exception
            log.info("An error occurred while creating the user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while creating the user: " + e.getMessage());
        }
    }

    public ResponseObject authenticate(String username, String password) {
        UserModel findUser = userRepository.findByUserName(username);
        boolean matches = passwordEncoder.matches(password, findUser.getPassword());
        if (!matches) throw new BadCredentialsException("Bad credentials");
        else {
            var token = tokenService.generateToken(findUser);

            AuthenticateRequest authenticateRequest = AuthenticateRequest.builder()
                    .token(token)
                    .authenticated(true)
                    .build();

            return ResponseObject.success(authenticateRequest);
        }
    }

//    public ResponseObject validateTokenResponse(ValidateRequest request)
//            throws JOSEException, ParseException {
//        var token = request.getToken();
//
//        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
//
//        SignedJWT signedJWT = SignedJWT.parse(token);
//
//        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//
//        var verified = signedJWT.verify(verifier);
//
//        ValidateTokenResponse validateTokenResponse = ValidateTokenResponse.builder()
//                .valid(verified && expiryTime.after(new Date()))
//                .build();
//        return ResponseObject.success(validateTokenResponse);
//    }


}
