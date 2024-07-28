package site.dadangsinhhoc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Data
public class userModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16, nullable = false)
    private String id;

    private String city;

    private String district;

    @Length(min = 5, message = "*Hãy nhập với tên đăng nhập với ít nhất là 5 kí tự!")
    @NotEmpty(message = "*Hãy cung cấp lại tên của bạn")
    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String userName;

    @Length(min = 5, message = "*Mật khẩu ít nhất là 5 kí tự")
    @NotEmpty(message = "*Hãy cung cấp lại mật khẩu")
    @Column(length = 128, columnDefinition = "varchar(128) default null")
    private String password;

    private String email;

    private String phone;

    private String avatar;

    private String gender;

    private LocalDate dob;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastSigninedTime;

    private boolean isAdmin;

    private boolean status;

    private String secretKey;

    private String role;
}
