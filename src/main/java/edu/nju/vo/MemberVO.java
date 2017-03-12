package edu.nju.vo;

import edu.nju.entity.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
@Table(name = "membership")
public class MemberVO {
    @Id
    @Column(name = "id")
    private int id;
    private String name;
//    private String password;
    private int savings;
    @Basic
    @Column(name = "bank_card")
    private int bankCard;
    private int credit;
    @Basic
    @Column(name = "register_at")
    private Date registerAt;
    @Basic
    @Column(name = "suspend_at")
    private Date suspendAt;
    @Basic
    @Column(name = "is_active")
    private boolean isActive;
    @Basic
    @Column(name = "is_cancelled")
    private boolean isCancelled;
    @OneToMany(mappedBy = "customer")
    private Collection<Room> rooms;

}
