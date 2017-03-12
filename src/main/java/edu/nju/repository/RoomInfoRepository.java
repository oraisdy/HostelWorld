package edu.nju.repository;

import edu.nju.entity.RoomInfo;
import edu.nju.entity.RoomType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
public interface RoomInfoRepository extends CrudRepository<RoomInfo,Integer> {

    List<RoomInfo> findByBranch(Integer branchId);

    @Modifying
    @Query("update RoomInfo r set r.type=?1, r.price=?2 where r.id = ?3")
    int updateById(RoomType roomType, double price, int id);
}
