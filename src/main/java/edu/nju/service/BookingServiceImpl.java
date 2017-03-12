package edu.nju.service;

import edu.nju.entity.Booking;
import edu.nju.repository.BookingRepository;
import edu.nju.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/3/12.
 */
@Service
public class BookingServiceImpl {

    public List<Booking> findNotCancelledBookings(Integer branchId) {
        return bookingRepository.findNotCancelledByBranchId(branchId);
    }

    public List<Booking> findNotCancelledBookingsByBranchAndMember(Integer branchId, Integer memberId) {
        return bookingRepository.findNotCancelledByBranchIdAndMemberId(branchId, memberId);
    }

    @Transactional
    public boolean checkIn(Integer bookingId,Date checkInTime) {
        return bookingRepository.setCheckInById(bookingId,checkInTime) > 0;
    }

    @Transactional
    public boolean checkOut(Integer bookingId,Date checkOutTime) {
        return bookingRepository.setCheckOutById(bookingId,checkOutTime) > 0;
    }

    @Transactional
    public boolean cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findOne(bookingId);
        return this.cancelBooking(booking);
    }

    @Transactional
    public boolean cancelBooking(Booking booking) {
        return roomRepository.setBookingById(booking.getRoom().getId(), false) > 0 && bookingRepository.setCancelledById(booking.getId()) > 0;
    }


    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomRepository roomRepository;
}
