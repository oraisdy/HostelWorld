/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.nju.controller;

import edu.nju.entity.CreateBranch;
import edu.nju.entity.Member;
import edu.nju.service.BranchServiceImpl;
import edu.nju.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Greg Turnquist
 */

@Controller
public class HomeController {


	@RequestMapping(value = "/")
	public String index(Model model) {
		return "redirect:/branches";
	}

	@RequestMapping(value = "/openShop")
	public String openShop(){return "branchForm";}

	@RequestMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("member", new Member());
		return "memberForm";
	}

	@RequestMapping(value = "/postRegister", method = RequestMethod.POST)
	public String postMember(@ModelAttribute("member") Member member) {
		userService.saveMember(member);
		return "redirect:/login";
	}

	@RequestMapping(value = "/postOpenShop", method = RequestMethod.POST)
	public @ResponseBody
	CreateBranch postApplication( @ModelAttribute CreateBranch branchForm) {
		System.out.println(branchForm);
		return branchService.createManagerThenOpenShop(branchForm);
	}


	@Autowired
	UserServiceImpl userService;
	@Autowired
	BranchServiceImpl branchService;
}
