package edu.nju.service;

import edu.nju.entity.Room;
import edu.nju.entity.RoomInfo;
import edu.nju.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
@Service
public class RoomServiceImpl {

    public Iterable<RoomInfo> findAllRoomPlanByBranch(Integer id) {
        return roomInfoRepository.findByBranch(id);
    }

    @Transactional
    public RoomInfo updateRoomPlan(RoomInfo info) {
        return roomInfoRepository.save(info);
    }


    @Transactional
    public RoomInfo createRoomPlan(RoomInfo info) {
        RoomInfo roomInfo = roomInfoRepository.save(info);
        if(roomInfo != null) {
            Room room = new Room();
            room.setBranchId(roomInfo.getBranch());
            room.setInfo(roomInfo);
            roomRepository.save(room);
        }
        return roomInfo;
    }

    public List<Room> findAvaiableRooms(Integer branchId, Date start, Date leave) {
        return roomRepository.findByStartDateAndEndDateAndBranchId(branchId, start, leave);
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
