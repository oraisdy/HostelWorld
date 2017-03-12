package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/11.
 */
public class BillRepositoryTest extends DataGeneralTest {
    @Test
    public void setSettled() throws Exception {
        System.out.println(billRepository.setSettled(1,new Date(System.currentTimeMillis())));
    }

    @Autowired BillRepository billRepository;
}