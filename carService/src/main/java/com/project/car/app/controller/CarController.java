package com.project.car.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/car")
public class CarController {

	@GetMapping(value = "/loadcarsdata")
	public List<String> loadAllChannels(Principal principal) {
		List<String> channelData = new ArrayList<>();
		return channelData;
	}

}