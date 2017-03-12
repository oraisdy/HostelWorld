package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
public class BookingRepositoryTest extends DataGeneralTest {
    @Test
    public void setBillById() throws Exception {
        System.out.println(bookingRepository.setBillById(10,9));
    }

    @Test
    public void setCheckInById() throws Exception {
        System.out.println(bookingRepository.setCheckInById(10, new Date(System.currentTimeMillis())));
    }

    @Test
    public void setCheckOutById() throws Exception {
        System.out.println(bookingRepository.setCheckOutById(10, new Date(System.currentTimeMillis())));
    }

    @Test
    public void findByBranchId() throws Exception {
        System.out.println(bookingRepository.findByBranchId(1));
    }

    @Test
    public void findByBranchIdAndCheckIn() throws Exception {
        System.out.println(bookingRepository.findByBranchIdAndCheckIn(true,1));

    }

    @Test
    public void setCancelledById() throws Exception {
        System.out.println(bookingRepository.setCancelledById(10));
    }

    @Test
    public void findByMemberId() throws Exception {
        System.out.println(bookingRepository.findByMemberId(1));
    }

    @Autowired
    BookingRepository bookingRepository;
}