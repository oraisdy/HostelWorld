package edu.nju.service;

import edu.nju.entity.Bill;
import edu.nju.repository.BillRepository;
import edu.nju.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
@Service
public class BillServiceImpl {

    @Transactional
    public void settleBill(Integer billId) {
        billRepository.setSettled(billId, new Date(System.currentTimeMillis()));
        Bill bill = billRepository.findOne(billId);
        branchRepository.increBalanceById(bill.getBranch().getId(), bill.getTotal());
    }

    public List<Bill> findAllNotSettled() {
        return billRepository.findAllNotSettled();
    }

    @Autowired
    BillRepository billRepository;
    @Autowired
    BranchRepository branchRepository;
}
