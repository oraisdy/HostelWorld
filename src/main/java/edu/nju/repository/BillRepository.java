package edu.nju.repository;

import edu.nju.entity.Bill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/2/26.
 */
public interface BillRepository extends CrudRepository<Bill,Integer> {

    List<Bill> findByMemberId(Integer memberId);

    List<Bill> findByBranchId(Integer branchId);

    @Query("select b from Bill b where b.isSettled=0 and b.payment='会员卡'")
    List<Bill> findAllNotSettled();

    @Modifying
    @Query("update Bill b set b.settleDate=?2, b.isSettled=1 where b.id=?1")
    int setSettled(Integer billId, Date time);
}
