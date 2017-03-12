package edu.nju.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dora on 2017/3/9.
 */
@Data
@Entity
//@IdClass(RoomInfoPK.class)
public class RoomInfo implements Serializable {

//    @Id
//    private Integer id;
    @Id @Column(name = "id") @GeneratedValue
    private Integer infoId;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private Double price;
    @Column(name = "num")
    private Integer number;
    private Integer branch;
//    @Id
//    @Column(name = "branch")
//    private Integer branch;
}
