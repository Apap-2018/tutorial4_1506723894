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
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);

        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute FlightModel flight){
        flightService.addFlight(flight);
        return "add";
    }

    @RequestMapping(value = "/flight/delete/{flightNumber}", method = RequestMethod.GET)
    private String deleteFlight(@PathVariable(value = "flightNumber") String flightNumber){
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        flightService.deleteFlight(flight);
        return "delete";
    }

    @RequestMapping(value = "/flight/view/{flightNumber}", method = RequestMethod.GET)
    private String getDetailFlightByFlightNumber(@PathVariable(value = "flightNumber") String flightNumber, Model model){
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        System.out.println(flight.getFlightNumber());
        model.addAttribute("flight", flight);
        return "view-flight";
    }

    @RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "flightNumber") String flightNumber, Model model){
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        return "updateFlight";
    }

    @RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.POST)
    private String updateFlightSubmit(@ModelAttribute FlightModel flight, @PathVariable(value = "flightNumber") String flightNumber){
        flightService.updateFlight(flightNumber, flight);
        return "update";
    }
}
