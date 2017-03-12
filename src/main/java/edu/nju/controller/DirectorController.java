package edu.nju.controller;

import edu.nju.entity.Bill;
import edu.nju.service.BillServiceImpl;
import edu.nju.service.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dora on 2017/3/11.
 */
@Controller
@Secured("ROLE_DIRECTOR")
@RequestMapping("/director")
public class DirectorController {

    @RequestMapping("/applications")
    public String applications(Model model) {
        model.addAttribute("modifyApplications",branchService.findAllModifyApplications());
        model.addAttribute("createApplications",branchService.findAllOpenShopApplications());
        return viewPrefix+"/applications";
    }

    @RequestMapping(value = "/rejectModify", method = RequestMethod.POST)
    public @ResponseBody void rejectApplication1(@ModelAttribute("id") int id) {
        branchService.rejectModifyBranch(id);
    }

    @RequestMapping(value = "/rejectCreate", method = RequestMethod.POST)
    public @ResponseBody void rejectApplication2(@ModelAttribute("id") int application) {
        branchService.rejectNewBranch(application);
    }

    @RequestMapping(value = "/approveModify", method = RequestMethod.POST)
    public @ResponseBody void approveModify(@ModelAttribute("id") int applicationId) {
        branchService.approveModifyBranch(applicationId);
    }

    @RequestMapping(value = "/approveCreate", method = RequestMethod.POST)
    public @ResponseBody void activateApplication(@ModelAttribute("id") int applicationId) {
        branchService.approveCreateBranch(applicationId);
    }

    @RequestMapping(value = "/settle", method = RequestMethod.POST)
    public @ResponseBody void settleBill(@ModelAttribute("id") int billId) {
        billService.settleBill(billId);
    }

    @RequestMapping("/bills")
    public String showBills(Model model) {
        model.addAttribute("bills", billService.findAllNotSettled());
        return viewPrefix+"/settle";
    }

    @RequestMapping(value = "/statistics")
    public String branchStatistics(Model model, @RequestParam(required = false,value = "branchId") Integer id) {
        List<Integer> list = branchService.findAllIds();
        System.out.println(list);
        System.out.println(id);
        model.addAttribute("idList",list);

        if (list!=null && !list.isEmpty()) {
            id = id == null ? list.get(0):id;
            List<Bill> bills = branchService.showBills(id);
            model.addAttribute("bills", bills);
            double total = 0;
            for (Bill bill:bills) {
                total += bill.getTotal();
            }
            model.addAttribute("income",total);
            model.addAttribute("bookings", branchService.showBookings(id));
            model.addAttribute("checkIns", branchService.showCheckIns(id));
        }
        return viewPrefix + "/statistics";
    }

    private String viewPrefix = "/generalManager";

    @Autowired
    BranchServiceImpl branchService;
    @Autowired
    BillServiceImpl billService;
}
