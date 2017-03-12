package edu.nju.service;

import edu.nju.entity.*;
import edu.nju.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dora on 2017/3/9.
 */
@Service
public class BranchServiceImpl {

    public Branch saveBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public Iterable<Branch> findAll() {
        return branchRepository.findAll();
    }

    public List<Integer> findAllIds() {
        return branchRepository.findIds();
    }

    public Branch findBranchById(Integer integer) {
        return branchRepository.findOne(integer);
    }

    public Branch findByManagerName(String username) {
        return branchRepository.findByManagerName(username);
    }

    public List<Booking> showBookings(Integer branchId) {
        return bookingRepository.findNotCancelledByBranchId(branchId);
    }

    public List<Bill> showBills(Integer branchId) {
        return billRepository.findByBranchId(branchId);
    }

    public List<Booking> showCheckIns(Integer branchId) {
        return bookingRepository.findByBranchIdAndCheckIn(true, branchId);
    }

    @Transactional
    public int rejectNewBranch(Integer appication) {
        return newBranchRepository.setRejected(appication);
    }

    @Transactional
    public int rejectModifyBranch(Integer appication) {
        return modifyBranchRepository.setRejected(appication);
    }

    public List<CreateBranch> findAllOpenShopApplications() {
        return newBranchRepository.findAllNotRejectedOrApproved();
    }

    public List<ModifyBranch> findAllModifyApplications() {
        return modifyBranchRepository.findAllNotRejectedOrApproved();
    }


    @Transactional
    public ModifyBranch saveModifyApplication(ModifyBranch modifyBranch) {
        modifyBranch.setTime(new Date(System.currentTimeMillis()));
        return modifyBranchRepository.save(modifyBranch);
    }

    @Transactional
    public Branch approveCreateBranch(Integer applicationId) {
        newBranchRepository.setApproved(applicationId);
        CreateBranch application= newBranchRepository.findOne(applicationId);
        Manager manager = new Manager();
        manager.setName(application.getUsername());
        manager.setPassword(application.getPassword());
        manager = managerRepository.save(manager);
        User user = new User();
        user.setPassword(application.getPassword());
        user.setRole(Role.MANAGER);
        user.setUsername(application.getUsername());
        userRepository.save(user);
        Branch branch = new Branch();
        branch.setAddress(application.getAddress());
        branch.setContact(application.getContact());
        branch.setManagerId(manager.getId());
        branch.setName(application.getName());
        branch.setDescription(application.getDescription());
        branch.setManagerName(manager.getName());
        branch.setBalance(0.0);
        return branchRepository.save(branch);
    }

    @Transactional
    public CreateBranch createManagerThenOpenShop(CreateBranch createBranch) {
        createBranch.setTime(new Date(System.currentTimeMillis()));
        return newBranchRepository.save(createBranch);
    }

    @Transactional
    public Branch approveModifyBranch(Integer applicationId) {
        modifyBranchRepository.setApproved(applicationId);
        ModifyBranch application= modifyBranchRepository.findOne(applicationId);
        Branch template = this.findBranchById(application.getBranchId());
        Branch branch = new Branch();
        branch.setId(template.getId());
        branch.setAddress(application.getAddress());
        branch.setContact(application.getContact());
        branch.setManagerId(application.getManagerId());
        branch.setName(application.getName());
        branch.setDescription(application.getDescription());
        branch.setBalance(template.getBalance());
        branch.setManagerName(template.getManagerName());
        return branchRepository.save(branch);
    }

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    CreateBranchRepository newBranchRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModifyBranchRepository modifyBranchRepository;
}
