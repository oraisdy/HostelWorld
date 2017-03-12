package edu.nju;

import edu.nju.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dora on 2017/3/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = Application.class)
public class ServiceGeneralTest {
}
