package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dora on 2017/3/11.
 */
public class UserRepositoryTest extends DataGeneralTest {
    @Test
    public void findByUsername() throws Exception {
        System.out.println(userRepository.findByUsername("dy"));
    }

    @Autowired
    UserRepository userRepository;

}