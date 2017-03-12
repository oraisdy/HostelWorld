package edu.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Created by Dora on 2017/3/11.
 */
@Data
@Entity
public class User {

    @Id
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
