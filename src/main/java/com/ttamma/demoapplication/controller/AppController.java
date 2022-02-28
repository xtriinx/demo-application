package com.ttamma.demoapplication.controller;

import java.util.List;

import com.ttamma.demoapplication.model.Country;
import com.ttamma.demoapplication.model.User;
import com.ttamma.demoapplication.services.CountryServices;
import com.ttamma.demoapplication.services.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ttamma.demoapplication.model.Client;
import com.ttamma.demoapplication.services.ClientServices;

@Controller
public class AppController {
	
	@Autowired
	private ClientServices clientServices;
	@Autowired
	private UserServices userServices;
	@Autowired
	private CountryServices countryServices;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		User currentUser = userServices.findUserByUsername(currentPrincipalName);

		List<Client> listClient = clientServices.listAll(currentUser.getId());
		model.addAttribute("listClient", listClient);
		model.addAttribute("user", currentUser);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newClientPage(Model model) {
		Client client = new Client();
		List<Country> countries = countryServices.listAllCountries();

		model.addAttribute("client", client);
		model.addAttribute("countries", countries);
		return "new-client";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveClient(@ModelAttribute("client") Client client) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		User currentUser = userServices.findUserByUsername(currentPrincipalName);

		client.setUserId(currentUser.getId());
		clientServices.save(client);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditClientPage(@PathVariable (name="id") int id) {
		Client client = clientServices.get(id);
		User currentUser = getCurrentUser();

		if (client.getUserId().equals(currentUser.getId())) {
			ModelAndView mav = new ModelAndView("edit_client");
			List<Country> countries = countryServices.listAllCountries();
			mav.addObject("client", client);
			mav.addObject("countries", countries);
			return mav;
		}

		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/delete/{sid}")
	public String deleteClientPage(@PathVariable (name="sid") int id) {
		clientServices.delete(id);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		return "redirect:/";
	}

	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		User currentUser = userServices.findUserByUsername(currentPrincipalName);
		return currentUser;
	}
}
