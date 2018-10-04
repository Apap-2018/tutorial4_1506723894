package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @Autowired
    private FlightService flightService;

    @RequestMapping("/")
    private String home(){
        return "home";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("pilot", new PilotModel());
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilot){
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
    private String getDetailPilotByLicenseNumber(@RequestParam("licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        List<FlightModel> listFlights = flightService.getFlightByPilotLicenseNumber(licenseNumber);

        model.addAttribute("pilot", pilot);
        model.addAttribute("listFlights", listFlights);
        return "view-pilot";
    }

    @RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
    private String delete(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flightService.deleteFlightByPilotLicenseNumber(licenseNumber);
        pilotService.deletePilot(pilot);
        return "delete";
    }

    @RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot", pilot);
        return "updatePilot";
    }

    @RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
    private String updatePilotSubmit(@ModelAttribute PilotModel pilot, @PathVariable(value = "licenseNumber") String licenseNumber){
        pilotService.updatePilot(licenseNumber, pilot);
        return "update";
    }
}
