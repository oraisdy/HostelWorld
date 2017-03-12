package edu.nju.repository;

import edu.nju.DataGeneralTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2017/3/10.
 */
public class MemberRepositoryTest extends DataGeneralTest {
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