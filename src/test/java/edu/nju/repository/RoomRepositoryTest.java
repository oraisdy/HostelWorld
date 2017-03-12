package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
public class RoomRepositoryTest extends DataGeneralTest {
    @Test
    public void setBookingById() throws Exception {
        System.out.println(roomRepository.setBookingById(1,false));
    }

    @Test
    public void findByStartDateAndEndDateAndBranchId() throws Exception {
        System.out.println(roomRepository.findByStartDateAndEndDateAndBranchId(1,new Date(Date.valueOf("2017-3-9").getTime()),new Date(Date.valueOf("2017-3-10").getTime())).size());
        System.out.println(roomRepository.findByStartDateAndEndDateAndBranchId(1,new Date(Date.valueOf("2017-3-9").getTime()),new Date(Date.valueOf("2017-3-10").getTime())));
    }


    @Autowired
    RoomRepository roomRepository;
}