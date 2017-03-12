package edu.nju.service;

import edu.nju.ServiceGeneralTest;
import edu.nju.entity.RoomInfo;
import edu.nju.entity.RoomType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
public class RoomServiceImplTest extends ServiceGeneralTest {
    @Test
    public void updateRoomPlan() throws Exception {
        RoomInfo info = new RoomInfo();
        info.setNumber(111);
        info.setPrice(800.0);
        info.setType(RoomType.大床房);
        System.out.println(roomService.updateRoomPlan(info));
    }


    Date start = new Date(Date.valueOf("2017-3-23").getTime());
    Date leave = new Date(Date.valueOf("2017-3-25").getTime());



    @Test
    public void findAvaiableRooms() throws Exception {
    }

    @Test
    public void cancelBooking() throws Exception {
        System.out.println(bookingService.cancelBooking(10));
    }


    @Autowired
    BookingServiceImpl bookingService;
    @Autowired
    RoomServiceImpl roomService;
}