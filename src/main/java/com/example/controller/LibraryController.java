package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Library;
import com.example.service.LibraryService;

@Controller
@RequestMapping("library")
public class LibraryController {
	private final LibraryService libraryService;
	
	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries",libraries);
		return "library/index";
	}
}
