package com.project.motorcycle.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.motorcycle.app.model.Channel;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class motorCycleController {

	@GetMapping(value = "/loadAlldata")
	public List<Channel> loadAllChannels(Principal principal) {
		List<Channel> channelData = new ArrayList<>();
		return channelData;
	}

}