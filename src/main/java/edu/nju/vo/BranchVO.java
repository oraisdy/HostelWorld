package edu.nju.vo;

import edu.nju.entity.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Branch info for presentation.
 * Created by Dora on 2017/2/21.
 */
@Data
public class BranchVO {

    private Branch branch;
    private List<RoomStock> roomStocks;
//    private List<Room> rooms;

}
