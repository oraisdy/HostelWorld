package edu.nju.vo;

import edu.nju.entity.Branch;
import edu.nju.entity.Member;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Dora on 2017/2/24.
 */

@Entity
@Data
public class RoomVO {
    @Id
    @Column(name = "id")
    private int id;
    private String type;
    private double price;
    private int number;
    private Date date;
    private boolean booking;
    private boolean checkIn;
    private boolean checkOut;
    private String payment;
    private boolean settled;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @Column(insertable=false, updatable=false)
    private Member customer;

}


