package com.cjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjd.service.PortalService;

@Controller
public class PortalController {

	@Autowired
	private PortalService portalService;
	
	@RequestMapping("/showBigPic")
	public String showBigPic(Model model) throws Exception {
		model.addAttribute("ad1", portalService.selBigContent());
		return "/index";
	}
}
