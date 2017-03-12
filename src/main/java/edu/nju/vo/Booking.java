package edu.nju.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/2/25.
 */
@Data
public class Booking {

    private Integer id;
    private Integer branchId;
    private Integer memberId;
    private List<Integer> roomIds;
    private double total;
    private Date date;
    private boolean isCancelled;
}
