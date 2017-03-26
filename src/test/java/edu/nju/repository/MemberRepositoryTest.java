package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/10.
 */
public class MemberRepositoryTest extends DataGeneralTest {
    @Test
    public void cancelMemberOverdue() throws Exception {
        System.out.println(memberRepository.cancelMemberOverdue(new Date(System.currentTimeMillis()),365));

    }

    @Test
    public void setMemberActive() throws Exception {
        System.out.println(memberRepository.setMemberActive(1,true));
    }

    @Test
    public void suspendMemberOverYear() throws Exception {
        System.out.println(memberRepository.suspendMemberOverdue(new Date(System.currentTimeMillis()),365,1000.0));
    }

    @Test
    public void decreSavingsById() throws Exception {
        System.out.println(memberRepository.decreSavingsById(1,100));
    }

    @Test
    public void resetCreditToZero() throws Exception {
        System.out.println(memberRepository.resetCreditToZero(1));
    }

    @Test
    public void increCreditById() throws Exception {
        System.out.println(memberRepository.increCreditById(1,100));
    }


    @Autowired
    MemberRepository memberRepository;
}