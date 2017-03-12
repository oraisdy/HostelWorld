package edu.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
public class Branch {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String address;
    private String contact;
    private Integer managerId;
    private String managerName;
    private Double balance;
}
