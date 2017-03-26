package edu.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
@Table(name = "membership")
public class Member {

    @Id @GeneratedValue
    private int id;
    private String password;
    private String name;
    private double savings;
    private String bankCard;
    private int credit;
    private Date registerAt;
    private Date suspendAt;
    private boolean isActive;
    private boolean isCancelled;
//    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
//    private Collection<Room> rooms;

}
