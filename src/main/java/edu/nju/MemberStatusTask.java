package edu.nju;

import edu.nju.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/14.
 */
@Component
public class MemberStatusTask {

    @Autowired
    MemberRepository memberRepository;

    @Scheduled(cron = "0 13 21 * * ?")
    @Transactional
    public void job() {
        System.out.println("Schedule Starts");
        Date date = new Date(System.currentTimeMillis());
        memberRepository.cancelMemberOverdue(date,365);
        memberRepository.suspendMemberOverdue(date,365,1000.0);
    }
}
