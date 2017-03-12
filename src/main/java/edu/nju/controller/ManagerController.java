package edu.nju.controller;

import edu.nju.config.SecurityConfig;
import edu.nju.entity.Branch;
import edu.nju.entity.ModifyBranch;
import edu.nju.entity.Payment;
import edu.nju.entity.RoomInfo;
import edu.nju.service.BookingServiceImpl;
import edu.nju.service.BranchServiceImpl;
import edu.nju.service.BusinessServiceImpl;
import edu.nju.service.RoomServiceImpl;
import edu.nju.vo.Business;
import edu.nju.vo.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
@Controller
@Secured("ROLE_MANAGER")
@RequestMapping("/manager")
public class ManagerController {



    @RequestMapping("/accommodate")
    public String accommodate(Model model, @ModelAttribute("range") DateRange rangeForm, @RequestParam(required = false,name = "memberId") Integer memberId) {
        if(rangeForm==null ||rangeForm.getFrom()==null) {
            rangeForm = new DateRange(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000 * 24 * 3600));
            model.addAttribute("range", new DateRange(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000 * 24 * 3600)));
        }
        DateRange range = new DateRange(new Date(rangeForm.getFrom().getTime()), new Date(rangeForm.getTo().getTime()));
        String username = SecurityConfig.currentUserDetails().getUsername();
        Branch branch = branchService.findByManagerName(username);
        model.addAttribute("branch", branch);
        if(memberId == null)
            model.addAttribute("bookings",bookingService.findNotCancelledBookings(branch.getId()));
        else
            model.addAttribute("bookings",bookingService.findNotCancelledBookingsByBranchAndMember(branch.getId(),memberId));
        return viewPrefix+"/accommodate";
    }

    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    public @ResponseBody boolean checkIn(Model model, @ModelAttribute("id") int bookingId) {
        return bookingService.checkIn(bookingId, new Date(System.currentTimeMillis()));
    }

    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public @ResponseBody boolean checkOut(Model model, @ModelAttribute("id") int bookingId) {
        return bookingService.checkOut(bookingId, new Date(System.currentTimeMillis()));
    }


    @RequestMapping(value = "/payBillByCash", method = RequestMethod.POST)
    public @ResponseBody
    Business payBill1(@ModelAttribute("id") int bookingId) {
        return businessService.payBill(bookingId, Payment.现金);
    }

    @RequestMapping(value = "/payBillByMembership", method = RequestMethod.POST)
    public @ResponseBody Business payBill2(@ModelAttribute("id") int bookingId) {
        return businessService.payBill(bookingId, Payment.会员卡);
    }

    @RequestMapping(value="/makeRoomPlan",method = RequestMethod.GET)
    public String roomPlan(Model model) {
        Branch branch = getBranchByCurrentManager();
        model.addAttribute("branch", branch);
        model.addAttribute("plans", roomService.findAllRoomPlanByBranch(branch.getId()));
        model.addAttribute("form",new RoomInfo());
        return viewPrefix+"/plan";
    }

    @RequestMapping(value = "/makeRoomPlan",method = RequestMethod.POST)
    public String postPlan( @ModelAttribute("form") RoomInfo form) {
        form.setBranch(getBranchByCurrentManager().getId());
        roomService.updateRoomPlan(form);
        return "redirect:"+controllerPrefix+"/makeRoomPlan";
    }

    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    public String postAdd( @ModelAttribute("form") RoomInfo form) {
        form.setBranch(getBranchByCurrentManager().getId());
        roomService.createRoomPlan(form);
        return "redirect:"+controllerPrefix+"/makeRoomPlan";
    }

    @RequestMapping("/statistics")
    public String branchStatistics(Model model) {
        Branch branch = getBranchByCurrentManager();
        model.addAttribute("branch", branch);
        model.addAttribute("bills", branchService.showBills(branch.getId()));
        model.addAttribute("bookings", branchService.showBookings(branch.getId()));
        model.addAttribute("checkIns", branchService.showCheckIns(branch.getId()));

        return viewPrefix+"/statistics";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String editBranch(Model model) {
        Branch branch = getBranchByCurrentManager();
        model.addAttribute("branch", branch);
        return viewPrefix+"/modify";
    }

    @RequestMapping(value = "/postModify", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute Branch branchForm){
        Branch branch = getBranchByCurrentManager();
        ModifyBranch application = new ModifyBranch();
        application.setBranchId(branch.getId());
        application.setManagerId(branch.getManagerId());
        application.setName(branchForm.getName());
        application.setDescription(branchForm.getDescription());
        application.setAddress(branchForm.getAddress());
        application.setContact(branchForm.getContact());
        branchService.saveModifyApplication(application);
        return "redirect:"+controllerPrefix+"/modify";
    }

    private String viewPrefix = "branchManager";
    private String controllerPrefix = "/manager";

    @Autowired
    BranchServiceImpl branchService;
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    BusinessServiceImpl businessService;
    @Autowired
    BookingServiceImpl bookingService;

    private Branch getBranchByCurrentManager() {
        String username = SecurityConfig.currentUserDetails().getUsername();
        return branchService.findByManagerName(username);
    }
}
