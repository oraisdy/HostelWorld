package edu.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
@Table(name = "open_shop")
public class CreateBranch {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String address;
    private String contact;
    private boolean isRejected;
    private boolean isApproved;
    private String username;
    private String password;
    private Date time;

}
