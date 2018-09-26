package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList(){
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		PilotModel pilots = null;
		for (PilotModel comp : archivePilot) {
			if(comp.getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				pilots = comp;
				break;
			}
		}
		return pilots;
	}
	
	@Override
	public PilotModel getPilotDetailByIdNumber(String id) {
		PilotModel pilott = null;
		for (PilotModel compt : archivePilot) {
			if(compt.getId().equalsIgnoreCase(id)) {
				pilott = compt;
				break;
			}
		}
		return pilott;
	}
	
	
	

}
