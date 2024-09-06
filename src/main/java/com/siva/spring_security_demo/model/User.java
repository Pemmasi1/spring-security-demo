package com.siva.spring_security_demo.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@Setter
@Getter
@ToString
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "password")
    private String password;

}
