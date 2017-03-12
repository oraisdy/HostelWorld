package edu.nju.controller;

import edu.nju.service.BranchServiceImpl;
import edu.nju.service.BusinessServiceImpl;
import edu.nju.service.RoomServiceImpl;
import edu.nju.vo.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

/**
 * Created by Dora on 2017/3/9.
 */
@Controller
public class BranchController {

    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    BranchServiceImpl branchService;
    @Autowired
    BusinessServiceImpl businessService;

    private int memberId = 1;

    @RequestMapping(value = "/branch/{id}")
    public String getRooms(Model model, @PathVariable int id, @ModelAttribute("range") DateRange rangeForm) {

        // 表单中的自动填写日期
        if (rangeForm == null || rangeForm.getFrom() == null) {
            rangeForm = new DateRange(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000 * 24 * 3600));
            model.addAttribute("range", new DateRange(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000 * 24 * 3600)));
        }
        DateRange range = new DateRange(rangeForm.getFrom(), rangeForm.getTo());
        model.addAttribute("branch", branchService.findBranchById(id));
        model.addAttribute("rooms", range.getFrom().getTime() >= range.getTo().getTime() || range.getFrom().getTime() + 1000 * 24 * 3600 < System.currentTimeMillis() ? null : roomService.findAvaiableRooms(id, range.getFrom(), range.getTo()));
        return "booking";
    }


    @GetMapping("/branches")
    public String branches(Model model) {
        model.addAttribute("branches", branchService.findAll());
        return "index";
    }


}
