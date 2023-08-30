package com.poc.resortmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.poc.resortmanagementsystem.entity.Resort;
import com.poc.resortmanagementsystem.repository.ResortRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ResortController {
	
	private ResortRepository resortRepository;

	public ResortController(ResortRepository resortRepository) {
		
		this.resortRepository = resortRepository;
	}
	@GetMapping({ "/resort", "/list" })
	public ModelAndView getAllResorts() {
		ModelAndView mav = new ModelAndView("list-resorts");
		mav.addObject("resorts", resortRepository.findAll());
		return mav;
	}

	@GetMapping("/addResortForm")
	public ModelAndView addResortForm() {
		ModelAndView mav = new ModelAndView("add-resort-form");
		Resort newResort = new Resort();
		mav.addObject("resort", newResort);
		return mav;
	}

	@PostMapping("/saveResort")
	public String saveResort(@ModelAttribute Resort resort) {

		// Check if a resort with the same name already exists
		if (resortRepository.existsByName(resort.getName())) {
			throw new IllegalArgumentException("A resort with the same name already exists.");
		}
		else{
			resortRepository.save(resort);
			return "redirect:/resort";
		}

	}

	/*@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long resortId) {
		ModelAndView mav = new ModelAndView("add-resort-form");
		Resort resort = resortRepository.findById(resortId).get();
		mav.addObject("resort", resort);
		return mav;
	}*/

	@GetMapping("/updateResortForm/{id}")
	public ModelAndView updateResortForm(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("update-resort-form");
		Resort existingResort = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));
		mav.addObject("resort", existingResort);
		return mav;
	}

	@PostMapping("/updateResort/{id}")
	public String updateResort(@PathVariable Long id, @ModelAttribute Resort updatedResort) {
		Resort existingResort = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));

		// Check if the updated name conflicts with an existing resort's name
		if (!existingResort.getName().equals(updatedResort.getName()) &&
				resortRepository.existsByName(updatedResort.getName())) {
			throw new IllegalArgumentException("A resort with the updated name already exists.");
		}

		// Update the resort's properties
		existingResort.setName(updatedResort.getName());
		existingResort.setAddress(updatedResort.getAddress());

		resortRepository.save(existingResort);
		return "redirect:/resort";
	}


	/*@GetMapping("/deleteResort")
	public String deleteResort(@RequestParam Long resortId) {
		resortRepository.deleteById(resortId);
		return "redirect:/resort";
	}*/

	@GetMapping("/deleteResort/{id}")
	public String deleteResort(@PathVariable Long id) {
		Resort resortToDelete = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));

		resortRepository.delete(resortToDelete);
		return "redirect:/resort";
	}


}
