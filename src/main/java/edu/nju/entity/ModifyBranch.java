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
@Table(name = "modify_branch")
public class ModifyBranch {
    @Id
    @GeneratedValue
    private int id;
    private int branchId;
    private String name;
    private String description;
    private String address;
    private String contact;
    private boolean isRejected;
    private boolean isApproved;
    private Integer managerId;
    private Date time;
}
