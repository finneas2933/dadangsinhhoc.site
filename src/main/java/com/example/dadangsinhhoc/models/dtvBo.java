package com.example.dadangsinhhoc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class dtvBo {
    @Id
    private Long id;

    private String name;
    private String name_latinh;
    private int loai;
    private int id_dtv_lop;
    private int status;
    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime updated_at;
    private String updated_by;

}
