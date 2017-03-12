package edu.nju.vo;

import edu.nju.entity.Payment;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/2/25.
 */
@Data
public class BillVO {

    private Integer branchId;
    private Integer memberId;
    private List<Integer> roomIds;
    private double total;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Payment payment;

}
