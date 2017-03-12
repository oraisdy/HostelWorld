package edu.nju.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
public class Manager {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String password;

//    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
//    private Collection<Branch> branchesById;

}
