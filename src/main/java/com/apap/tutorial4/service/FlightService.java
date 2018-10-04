package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

import java.util.List;

public interface FlightService {

    void addFlight(FlightModel flight);

    void deleteFlight(FlightModel flight);

    FlightModel getFlighByFlightNumber(String flightNumber);

    List<FlightModel> getFlightByPilotLicenseNumber(String licenseNumber);

    void deleteFlightByPilotLicenseNumber(String licenseNumber);

    void updateFlight(String flightNumber, FlightModel flight);
}
