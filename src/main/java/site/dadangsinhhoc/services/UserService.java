package site.dadangsinhhoc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public ResponseObject findByEmail(String email) {
        UserModel user = userRepository.findByEmail(email);
        return ResponseObject.success(user);
    }

    public ResponseObject checkUserExists(String identifier) {
        boolean exists = userRepository.existsByEmail(identifier) ||
                userRepository.existsByPhone(identifier);

        if (exists) {
            return ResponseObject.success("User exists", true);
        } else {
            return ResponseObject.success("User does not exist", false);
        }
    }

    public ResponseObject createNewUser(UserModel user) {
        try {
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

            if (user.getRole() == null) {
                user.setRole("ROLE_USER");
            }
            user.setId(null);
            UserModel savedUser = UserModel.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .dob(user.getDob())
                    .address(user.getAddress())
                    .createdAt(now)
                    .updatedAt(now)
                    .lastSigninedTime(user.getLastSigninedTime())
                    .status(user.getStatus())
                    .role(user.getRole())
                    .build();
            UserModel savedUserModel = userRepository.save(savedUser);

            return ResponseObject.success("User created successfully", savedUserModel);
        } catch (Exception e) {
            log.info("An error occurred while creating the user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while creating the user: " + e.getMessage());
        }
    }

    public ResponseObject login(String email, String password) {
        UserModel user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseObject.error(ErrorCode.UNAUTHORIZED.getCode(), "Invalid password");
        }
        String token = tokenService.generateToken(user);
        return ResponseObject.success("Login successful", Map.of("token", token, "user", user));
    }

    public ResponseObject getCurrentUser() {
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseObject.error(ErrorCode.UNAUTHORIZED.getCode(), "User not authenticated");
            }

            String email = authentication.getName();
            UserModel currentUser = userRepository.findByEmail(email);

            if (currentUser == null) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
            }

            currentUser.setPassword(null);

            return ResponseObject.success("Current user retrieved successfully", currentUser);
        } catch (Exception e) {
            log.error("Error retrieving current user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving the current user");
        }
    }

    public ResponseObject deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
    }

    public ResponseObject updateUser(Integer id, UserModel updatedUser) {
        try {
            Optional<UserModel> existingUserOptional = userRepository.findById(id);
            if (existingUserOptional.isEmpty()) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
            }

            UserModel existingUser = existingUserOptional.get();

            // Cập nhật thông tin
            if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
            if (updatedUser.getGender() != null) existingUser.setGender(updatedUser.getGender());
            if (updatedUser.getDob() != null) existingUser.setDob(updatedUser.getDob());
            if (updatedUser.getAddress() != null) existingUser.setAddress(updatedUser.getAddress());
            if (updatedUser.getStatus() != null) existingUser.setStatus(updatedUser.getStatus());
            if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());

            existingUser.setUpdatedAt(LocalDateTime.now());

            UserModel savedUser = userRepository.save(existingUser);
            return ResponseObject.success("User updated successfully", savedUser);
        } catch (Exception e) {
            log.error("An error occurred while updating the user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while updating the user: " + e.getMessage());
        }
    }

    public ResponseObject getAllUsers() {
        try {
            List<UserModel> users = userRepository.findAll();
            users.forEach(user -> user.setPassword(null));
            return ResponseObject.success("All users retrieved successfully", users);
        } catch (Exception e) {
            log.error("An error occurred while retrieving all users: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving all users: " + e.getMessage());
        }
    }

    public ResponseObject countAllUsers() {
        try {
            long count = userRepository.count();
            return ResponseObject.success("Total number of users retrieved successfully", count);
        } catch (Exception e) {
            log.error("An error occurred while counting users: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while counting users: " + e.getMessage());
        }
    }

    public ResponseObject countUsersByRole() {
        try {
            Map<String, Long> roleCounts = new HashMap<>();
            List<UserModel> users = userRepository.findAll();
            for (UserModel user : users) {
                String role = user.getRole();
                roleCounts.put(role, roleCounts.getOrDefault(role, 0L) + 1);
            }
            return ResponseObject.success("User counts by role retrieved successfully", roleCounts);
        } catch (Exception e) {
            log.error("An error occurred while counting users by role: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while counting users by role: " + e.getMessage());
        }
    }

    public ResponseObject getUsersByRole(String role) {
        try {
            List<UserModel> users = userRepository.findByRole(role);
            users.forEach(user -> user.setPassword(null));
            return ResponseObject.success("Users with role '" + role + "' retrieved successfully", users);
        } catch (Exception e) {
            log.error("An error occurred while retrieving users by role: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving users by role: " + e.getMessage());
        }
    }
}
