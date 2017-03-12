package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dora on 2017/3/9.
 */
public class BranchRepositoryTest extends DataGeneralTest {

    @Test
    public void findByManagerId() throws Exception {
        System.out.println(branchRepository.findByManagerId(2));
    }

    @Autowired
    BranchRepository branchRepository;

}