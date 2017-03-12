package edu.nju.repository;

import edu.nju.entity.Booking;
import edu.nju.entity.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
public interface RoomRepository extends CrudRepository<Room,Integer> {

//    SELECT * FROM room WHERE
//            (start_date >= "2017-3-10" OR leave_date <= "2017-3-9")
//    OR room.booking=0 AND room.branch_id = 1
//    GROUP BY number
//    HAVING count(id)=(SELECT count(id) FROM room r2 WHERE r2.number=room.number);

    @Query("SELECT room FROM Room room WHERE room.startDate >= ?3 OR room.leaveDate <= ?2 " +
            " OR room.isBooking=0 AND room.branchId=?1 group by room.info.infoId " +
            " having count(room.id)=(SELECT count(r2.id) FROM Room r2 WHERE r2.info.infoId=room.info.infoId)")
    List<Room> findByStartDateAndEndDateAndBranchId(Integer branchId, Date start, Date leave);


    @Modifying
    @Query("update Room room set room.isBooking=?2 where room.id=?1")
    int setBookingById(Integer roomId,boolean booking);
//
//    void setBillIdById(Integer roomId, Integer billId);
//
//    void setSettledById(Integer roomId, boolean settled);

}
