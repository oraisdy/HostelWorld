package edu.nju.service;

import edu.nju.entity.*;
import edu.nju.repository.*;
import edu.nju.vo.Business;
import edu.nju.vo.BusinessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/10.
 */
@Service
public class BusinessServiceImpl {

    private Business checkMemberStatus(Member member) {
        if(member == null) return BusinessResult.ERROR;
        if(member.isCancelled()) return BusinessResult.SUSPENDED_ACCOUNT;
        if(!member.isActive()) return BusinessResult.INACTIVE_ACCOUNT;
        return BusinessResult.PASS;
    }

    @Transactional
    public Business bookThenPay(Integer roomId, Integer memberId, Date start, Date leave) {
        Business booking = bookRoom(roomId, memberId, start, leave);
        if(booking instanceof Booking) {
            return payBill((Booking) booking, Payment.会员卡);
        }else
            return booking;
    }

    @Transactional
    public Business payBill(Integer bookingId, Payment payment) {
        Booking booking = bookingRepository.findOne(bookingId);
        Business check = checkMemberStatus(booking.getMember());
        if (!check.getAsBoolean()) return check;
        return this.payBill(booking, payment);
    }


    private Business payBill(Booking booking, Payment payment) {

        if (booking != null && !booking.isCancelled()) {
            Member member = memberRepository.findOne(booking.getMember().getId());
            if(member == null) return BusinessResult.ERROR;
            Bill bill = new Bill();
            bill.setDate(new Date(System.currentTimeMillis()));
            bill.setPayment(payment);
            Branch branch = new Branch();
            branch.setId(booking.getBranchId());
            bill.setBranch(branch);
            bill.setMemberId(member.getId());
            bill.setTotal(booking.getTotal());
            bill.setRoom(booking.getRoom());
            switch (bill.getPayment()) {
                case 现金:
                    break;
                case 会员卡:
                    double cost = DiscountLevel.decide(member.getCredit()).convert(booking.getTotal());
                    if(member.getSavings() < cost) {
                        System.out.println("余额不足"+member.getSavings()+" "+cost);
                        return BusinessResult.INSUFFICIENT_FUNDS;
                    }
                    bill.setTotal(cost);
                    memberRepository.increCreditById(member.getId(), (int)booking.getTotal());
                    memberRepository.decreSavingsById(member.getId(), cost);
                    break;
            }
            bill = billRepository.save(bill);
            bookingRepository.setBillById(booking.getId(),bill.getId());
            return BusinessResult.PASS;
        } else
            return BusinessResult.ERROR;
    }


    @Transactional
    public Business bookRoom(Integer roomId, Integer memberId, Date start, Date leave) {

        Member m = memberRepository.findOne(memberId);
        Business check = checkMemberStatus(m);
        if (!check.getAsBoolean()) return check;

        Room roomTemplate = roomRepository.findOne(roomId);
        Room room = new Room();
        room.setBooking(true);
        room.setBranchId(roomTemplate.getBranchId());
        room.setInfo(roomTemplate.getInfo());
        room.setStartDate(start);
        room.setLeaveDate(leave);
        room = roomRepository.save(room);

        Booking booking = new Booking();
        Member member = new Member();
        member.setId(memberId);
        booking.setMember(member);
        booking.setRoom(room);
        booking.setBranchId(roomTemplate.getBranchId());
        booking.setDate(new Date(System.currentTimeMillis()));
        long gap = (long) (leave.getTime() - start.getTime()) / (1000 * 3600 * 24);
        booking.setTotal(gap * roomTemplate.getInfo().getPrice());
        booking = bookingRepository.save(booking);
        return booking;
    }

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    RoomInfoRepository roomInfoRepository;
    @Autowired
    MemberRepository memberRepository;
}
