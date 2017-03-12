package edu.nju.entity;

import edu.nju.entity.RoomType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2017/2/25.
 */
@Data
//@Entity
//@NamedQuery(name = "User.findByEmailAddress",
//        query = "select u from User u where u.emailAddress = ?1")
public class RoomStock {

    @Enumerated(EnumType.STRING)
    private RoomType type;
    private Double price;
    private Long amount;
    private Map<Integer,List<Room>> rooms;
    private List<Integer> roomNumbers;
//    private String rooms;



    //删了就报错，喵喵喵

//    public RoomStock(String type, double price, long amount) {
//        this.type = type;
//        this.price = price;
//        this.amount = amount;
//    }



//    public RoomStock(RoomType type, double price, long amount, String rooms) {
//        this.type = type;
//        this.price = price;
//        this.amount = amount;
//        this.rooms = rooms;
//    }

//    public RoomStock(RoomType type, double price, long amount) {
//        this.type = type;
//        this.price = price;
//        this.amount = amount;
//    }

//    public RoomStock() {
//    }
}
