package edu.nju.vo;

import edu.nju.entity.RoomType;
import lombok.Data;

import java.sql.Date;

/**
 * Created by Dora on 2017/2/28.
 */
@Data
public class ConsultForm {

    private Date from;
    private Date to;
    private RoomType type;
}
