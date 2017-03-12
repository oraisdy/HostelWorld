package edu.nju.controller;

import edu.nju.config.SecurityConfig;
import edu.nju.entity.Member;
import edu.nju.service.*;
import edu.nju.vo.Business;
import edu.nju.vo.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dora on 2017/3/9.
 */
@Controller
@Secured("ROLE_MEMBER")
@RequestMapping("/member")
public class MemberController {

    @RequestMapping(value = "/bookRoom")
    public @ResponseBody
    Business book(@ModelAttribute("room") int roomId, @ModelAttribute("range") DateRange range) {
        return businessService.bookRoom(roomId, getCurrentMember().getId(), range.getFrom(),range.getTo());
    }

    @RequestMapping(value = "/bookRoomAndPay")
    public @ResponseBody
    Business bookThenPay(@ModelAttribute("room") int roomId, @ModelAttribute("range") DateRange range) {
        return businessService.bookThenPay(roomId, getCurrentMember().getId(), range.getFrom(),range.getTo());
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String editMember(Model model) {
        Member member = userService.findMember(getCurrentMember().getId());
        model.addAttribute("member", member);
        model.addAttribute("level", userService.getDiscountLevel(member));
        return viewPrefix + "/modify";
    }

    @RequestMapping(value = "/postModify", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute Member memberForm){
        Member member = userService.findMember(getCurrentMember().getId());
        member.setBankCard(memberForm.getBankCard());
        userService.saveMember(member);
        return "redirect:/member/memberModify";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String memberStatistics(Model model){
        model.addAttribute("bookings",analysisService.showBookings(getCurrentMember().getId()));
        model.addAttribute("bills",analysisService.showBills(getCurrentMember().getId()));
        model.addAttribute("checkIns",analysisService.showCheckIns(getCurrentMember().getId()));
        return viewPrefix + "/statistics";
    }

    @RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
    public @ResponseBody
    boolean cancelBooking(Model model, @ModelAttribute("id") int bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public @ResponseBody void charge(@ModelAttribute("amount") double amount) {
        userService.charge(getCurrentMember().getId(),amount);
    }

    @RequestMapping(value = "/exchangeBalance", method = RequestMethod.POST)
    public @ResponseBody void exchange() {
        userService.exchangeBalance(getCurrentMember().getId());
    }

    @RequestMapping(value = "/cancelMembership", method = RequestMethod.POST)
    public @ResponseBody void cancel() {
        userService.cancelMembership(getCurrentMember().getId());
    }

    @RequestMapping(value = "/activateMembership", method = RequestMethod.POST)
    public @ResponseBody void activate() {
        userService.activateMembership(getCurrentMember().getId());
    }

    private String viewPrefix = "member";

    @Autowired
    AnalysisServiceImpl analysisService;
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BusinessServiceImpl businessService;
    @Autowired
    BookingServiceImpl bookingService;

    private Member getCurrentMember() {
        String username = SecurityConfig.currentUserDetails().getUsername();
        return userService.findMember(username);
    }
}
