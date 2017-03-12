package edu.nju.entity;

import edu.nju.entity.Payment;
import edu.nju.vo.Business;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/2/25.
 */
@Data
@Entity
public class Bill implements Business{
    @Id @GeneratedValue
    private Integer id;
    @OneToOne
    private Branch branch;
    private Integer memberId;
    @OneToOne
    private Room room;
    private double total;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private boolean isSettled;
    private Date settleDate;

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
