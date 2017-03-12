package edu.nju.service;

import edu.nju.entity.Bill;
import edu.nju.entity.Booking;
import edu.nju.repository.BillRepository;
import edu.nju.repository.BookingRepository;
import edu.nju.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
@Service
public class AnalysisServiceImpl {

    public List<Booking> showBookings(Integer memberId) {
        return bookingRepository.findByMemberId(memberId);
    }

    public List<Bill> showBills(Integer memberId) {
        return billRepository.findByMemberId(memberId);
    }

    public List<Booking> showCheckIns(Integer memberId) {
        return bookingRepository.findByMemberIdAndCheckIn(true, memberId);
    }

//    @Autowired
//    RoomRepository roomRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BillRepository billRepository;
}
