package edu.nju.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Dora on 2017/2/24.
 */

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private RoomInfo info;
    @Column(name = "booking")
    private boolean isBooking;
    private Date startDate;
    private Date leaveDate;
    private Integer branchId;

}


