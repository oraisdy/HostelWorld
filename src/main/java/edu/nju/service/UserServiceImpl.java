package edu.nju.service;

import edu.nju.entity.Member;
import edu.nju.entity.Role;
import edu.nju.entity.User;
import edu.nju.repository.MemberRepository;
import edu.nju.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
@Service
public class UserServiceImpl {

    final double exchangeRate = 0.01;



    @Transactional
    public void cancelMembership(Integer memberId) {
        memberRepository.setMemberCancelled(memberId, true);
    }

    @Transactional
    public void activateMembership(Integer memberId) {
        memberRepository.setMemberCancelled(memberId, false);
    }


    @Transactional
    public void exchangeBalance(Integer memberId) {
        Member member = memberRepository.findOne(memberId);
        memberRepository.increSavingsById(memberId, member.getCredit() * exchangeRate);
        memberRepository.resetCreditToZero(memberId);
    }

    @Transactional
    public void charge(Integer memberId, double amount) {
        memberRepository.increSavingsById(memberId,amount);
        if(amount >=  1000) {
            memberRepository.setMemberActive(memberId,true);
        }
    }

    public DiscountLevel getDiscountLevel(Member member){
        return DiscountLevel.decide(member.getCredit());
    }

    public Member findMember(Integer id) {
        return memberRepository.findOne(id);
    }

    public Member findMember(String username) { return memberRepository.findByName(username); }

    public Member saveMember(Member member) {
        User user = new User();
        user.setUsername(member.getName());
        user.setPassword(member.getPassword());
        user.setRole(Role.MEMBER);
        userRepository.save(user);
        member.setRegisterAt(new Date(System.currentTimeMillis()));
        return memberRepository.save(member);
    }

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserRepository userRepository;
}
