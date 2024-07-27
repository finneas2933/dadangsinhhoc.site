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

    @Column(length = 100, nullable = false, columnDefinition = "varchar(100) default '0'")
    private String city;

    @Column(length = 100, nullable = false, columnDefinition = "varchar(100) default '0'")
    private String district;

    @Length(min = 5, message = "*Hãy nhập với tên đăng nhập với ít nhất là 5 kí tự!")
    @NotEmpty(message = "*Hãy cung cấp lại tên của bạn")
    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String userName;

    @Length(min = 5, message = "*Mật khẩu ít nhất là 5 kí tự")
    @NotEmpty(message = "*Hãy cung cấp lại mật khẩu")
    @Column(length = 128, columnDefinition = "varchar(128) default null")
    private String password;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String email;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String phone;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String avatar;

    @Column(length = 10, columnDefinition = "varchar(10) default null")
    private String gender;

    @Column(columnDefinition = "date default null")
    private LocalDate dob;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String address;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "datetime default null")
    private LocalDateTime lastSignedInTime;

    @Column(nullable = false, columnDefinition = "tinyint(1) default '0'")
    private boolean isAdmin;

    @Column(nullable = false, columnDefinition = "tinyint(1) default '0'")
    private boolean status;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String secretKey;

    @Column(length = 255, columnDefinition = "varchar(255) default null")
    private String role;
}
