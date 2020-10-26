package com.artezio.register.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
@Getter
@Setter
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
}


