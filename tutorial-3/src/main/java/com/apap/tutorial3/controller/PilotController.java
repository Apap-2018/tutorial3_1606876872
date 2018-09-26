package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add (@RequestParam(value= "id", required = true) String id,
					   @RequestParam(value= "licenseNumber", required= true) String licenseNumber,
					   @RequestParam(value= "name", required = true) String name,
					   @RequestParam(value= "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value = {"/pilot/view/license-number/{licenseNumber}"})
	public String viewPath(@PathVariable String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	
		if (archive !=  null) {// karena dia ga berhasil add. jadi ya pasti archivenya null kan
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}else {
			model.addAttribute("licenseNum", licenseNumber);// karena dia null atau angka lain. kalo null ngapain di get
			return "license";
		}
		
	}
	
	@RequestMapping(value = {"/pilot/update/license-number/{licenseNumber}/fly-hour/{flyhour}"})
	public String flyhourPath(@PathVariable String licenseNumber, @PathVariable Integer flyhour, Model model ) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (archive !=  null) {
			model.addAttribute("pilot", archive);
			archive.setFlyHour(flyhour);
			return "change-success";	
		}else {
			return "change-failed";
		}
	}

	
	@RequestMapping(value = {"/pilot/delete/id/{idNum}"})
	public String idPath(@PathVariable String idNum,  Model model) {
		PilotModel archive = pilotService.getPilotDetailByIdNumber(idNum);
		List<PilotModel> archives = pilotService.getPilotList();
		if (archive !=  null) {
			archives.remove(archive);
			return "delete-success";
		}else {
			model.addAttribute("id", idNum);
			return "delete-failed";
		}
	}
	
	
	
	
	
	
	
	
	
}
