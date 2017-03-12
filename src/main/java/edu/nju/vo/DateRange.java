package edu.nju.vo;

import lombok.Data;

import java.sql.Date;

/**
 * Created by Dora on 2017/2/26.
 */
@Data
public class DateRange {

    private Date from;
    private Date to;

    public DateRange() {
    }

    public DateRange(Date from, Date to) {
        this.from = from;
        this.to = to;
    }
}
