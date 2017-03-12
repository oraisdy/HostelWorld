package edu.nju.repository;

import edu.nju.entity.ModifyBranch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dora on 2017/3/11.
 */
public interface ModifyBranchRepository extends CrudRepository<ModifyBranch, Integer> {

    @Query("select n from ModifyBranch n where n.isRejected=0 and n.isApproved=0")
    List<ModifyBranch> findAllNotRejectedOrApproved();

    @Modifying
    @Query("update ModifyBranch n set n.isApproved=1 where n.id=?1")
    int setApproved(Integer appicationId);

    @Modifying
    @Query("update ModifyBranch n set n.isRejected=1 where n.id=?1")
    int setRejected(Integer appicationId);
}
