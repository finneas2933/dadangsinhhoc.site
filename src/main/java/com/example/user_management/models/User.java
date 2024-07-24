package com.example.user_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private long id;

    private String name;
    private int age;
    private String address;

//    @OneToOne(mappedBy = "user")
//    @JsonManagedReference
//    private Company company;
}
