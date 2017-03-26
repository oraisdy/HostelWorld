package edu.nju.repository;

import edu.nju.entity.Manager;
import edu.nju.entity.Member;
import edu.nju.entity.Room;
import edu.nju.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by Dora on 2017/2/22.
 */
public interface MemberRepository extends CrudRepository<Member,Integer> {

    @Modifying
    @Query(value = "update membership set is_active = 0, suspend_at=?1 where date(?1)-date(register_at)>=?2 and savings<?3", nativeQuery = true)
    int suspendMemberOverdue(Date now, int days, Double savings);

    @Modifying
    @Query(value = "update membership set is_cancelled = 1, suspend_at=?1 where date(?1)-date(suspend_at)>=?2 ", nativeQuery = true)
    int cancelMemberOverdue(Date now, int days);

    @Modifying
    @Query("update Member m set m.isCancelled=?2 where m.id=?1")
    int setMemberCancelled(Integer memberId, boolean cancelled);

    @Modifying
    @Query("update Member m set m.isActive=?2,m.suspendAt=null where m.id=?1")
    int setMemberActive(Integer memberId, boolean active);

    @Modifying
    @Query("update Member m set m.credit=m.credit+?2 where m.id=?1")
    int increCreditById(Integer memberId, Integer increment);

    @Modifying
    @Query("update Member m set m.savings = m.savings-?2 where m.id=?1")
    int decreSavingsById(Integer memberId, double decrement);

    @Modifying
    @Query("update Member m set m.savings = m.savings+?2 where m.id=?1")
    int increSavingsById(Integer memberId, double increment);

    @Modifying
    @Query("update Member m set m.credit=0 where m.id=?1")
    int resetCreditToZero(Integer memberId);

    Member findByName(String username);

}
