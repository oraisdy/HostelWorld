package edu.nju.repository;

import edu.nju.entity.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/3/6.
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query("select b from Booking b where b.member.id=?1 and b.isCancelled=0")
    List<Booking> findByMemberId(Integer member);

    @Query("select b from Booking b where b.checkIn=?1 and b.member.id=?2 and b.isCancelled=0")
    List<Booking> findByMemberIdAndCheckIn(boolean checkIn, Integer member);

    @Modifying
    @Query("update Booking b set b.isCancelled=1 where b.id =?1")
    int setCancelledById(Integer id);

    @Modifying
    @Query("update Booking b set b.bill.id=?2 where b.id=?1")
    int setBillById(Integer id, Integer billId);

    @Modifying
    @Query("update Booking b set b.checkIn=1,b.checkInTime=?2 where b.id=?1")
    int setCheckInById(Integer id, Date date);

    @Modifying
    @Query("update Booking b set b.checkOut=1,b.checkOutTime=?2 where b.id=?1")
    int setCheckOutById(Integer id, Date date);


    List<Booking> findByBranchId(Integer branchId);

    @Query("select b from Booking b where b.branchId=?1 and b.isCancelled=0")
    List<Booking> findNotCancelledByBranchId(Integer branchId);

    @Query("select b from Booking b where b.branchId=?1 and b.member.id=?2 and b.isCancelled=0")
    List<Booking> findNotCancelledByBranchIdAndMemberId(Integer branchId,Integer memberId);

    @Query("select b from Booking b where b.checkIn=?1 and b.branchId=?2 and b.isCancelled=0")
    List<Booking> findByBranchIdAndCheckIn(boolean checkIn, Integer branch);
}
