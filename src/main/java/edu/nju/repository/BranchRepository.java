package edu.nju.repository;

import edu.nju.entity.Branch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dora on 2017/2/21.
 */
public interface BranchRepository extends CrudRepository<Branch, Integer> {

   Branch findByManagerId(int manager);

   @Modifying
   @Query("update Branch b set b.balance=b.balance+?2 where b.id=?1")
   int increBalanceById(Integer id, Double increment);

   Branch findByManagerName(String username);

   @Query("select b.id from Branch b")
   List<Integer> findIds();


}
