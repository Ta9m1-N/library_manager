package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Library;
import com.example.entity.Log;
import com.example.service.LibraryService;
import com.example.service.LogService;
import com.example.service.LoginUser;

@Controller
@RequestMapping("library")
public class LibraryController {
	private final LibraryService libraryService;
	private final LogService logService;
	
	@Autowired
	public LibraryController(LibraryService libraryService,LogService logService) {
		this.libraryService = libraryService;
		this.logService = logService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries",libraries);
		return "library/index";
	}
	
	@GetMapping("/borrow")
	public String borrowingForm(@RequestParam("id") Integer id,Model model) {
		Library library = this.libraryService.findById(id);
		List<Log> logs = this.logService.findAll();
		model.addAttribute("library",library);
		model.addAttribute("log",logs);
		return "library/borrowingForm";
	}
	
	@PostMapping("/borrow")
	public String borrow(@RequestParam("return_due_date") String returnDueDate,@RequestParam("id") Integer id,@AuthenticationPrincipal LoginUser loginUser) {
		this.libraryService.updateUserId(id,loginUser.getUser().getId());
		this.logService.insert(id,loginUser.getUser().getId(),LocalDateTime.now(),LocalDateTime.parse(returnDueDate+"T00:00:00"));
		return "redirect:/library";
	}
	
	@PostMapping("/return")
	public String returnBook(@RequestParam("id") Integer libraryId,@AuthenticationPrincipal LoginUser loginUser) {
		this.logService.updateReturnDate(libraryId,loginUser.getUser().getId(),LocalDateTime.now());
		this.libraryService.updateUserId(libraryId,0);
		return "redirect:/library";
	}
	
	@GetMapping("/history")
	public String history(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		List<Log> logs = this.logService.findByUserId(loginUser.getUser().getId());
		model.addAttribute("logs", logs);
		return "library/borrowHistory";
	}
}
