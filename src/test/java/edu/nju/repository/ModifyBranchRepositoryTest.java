package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dora on 2017/3/11.
 */
public class ModifyBranchRepositoryTest extends DataGeneralTest {
    @Test
    public void findAllNotRejected() throws Exception {
        System.out.println(modifyBranchRepository.findAllNotRejectedOrApproved());
    }

    @Test
    public void setRejected() throws Exception {
        System.out.println(modifyBranchRepository.setRejected(1));
    }

    @Autowired ModifyBranchRepository modifyBranchRepository;
}