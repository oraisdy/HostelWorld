package edu.nju.entity;

import edu.nju.vo.Business;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Dora on 2017/3/6.
 */
@Data
@Entity
public class Booking implements Business {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer branchId;
    @OneToOne
    private Member member;
    private Date date;
    private double total;
    @OneToOne
    private Room room;
    private boolean isCancelled;
    private boolean checkIn;
    private Date checkInTime;
    private boolean checkOut;
    private Date checkOutTime;
    @OneToOne
    private Bill bill;


    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public boolean getAsBoolean() {
        return true;
    }
}