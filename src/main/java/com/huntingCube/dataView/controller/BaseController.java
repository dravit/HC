package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.*;
import com.huntingCube.dataView.service.*;
import com.huntingCube.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by dgup27 on 5/20/2017.
 */
@SessionAttributes({"institutes", "locations", "passingYears", "programs", "streams", "clients", "clientStatuses", "clientPositions"})
public abstract class BaseController {

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientStatusService clientStatusService;

    @Autowired
    ClientPositionService clientPositionService;

    @Autowired
    InstituteService instituteService;

    @Autowired
    LocationService locationService;

    @Autowired
    PassingYearService passingYearService;

    @Autowired
    ProgramService programService;

    @Autowired
    StreamService streamService;

    @Autowired
    ResourceHistoryService resourceHistoryService;

    @Autowired
    ClientHistoryService clientHistoryService;

    @Autowired
    ResourceService resourceService;

    @ModelAttribute("clients")
    public List<Client> initializeClients() {
        return clientService.findAllClients();
    }

    @ModelAttribute("clientStatuses")
    public List<ClientStatus> initializeClientStatues() {
        return clientStatusService.findAllStatus();
    }

    @ModelAttribute("clientPositions")
    public List<ClientPosition> initializeClientPositions() {
        return clientPositionService.findAllPositions();
    }

    @ModelAttribute("institutes")
    public List<Institute> initializeInstitutes() {
        return instituteService.findAllInstitutes();
    }

    @ModelAttribute("locations")
    public List<Location> initializeLocations() {
        return locationService.findAllLocations();
    }

    @ModelAttribute("passingYears")
    public List<PassingYear> initializePassingYears() {
        return passingYearService.findAllPassingYears();
    }

    @ModelAttribute("programs")
    public List<Program> initializePrograms() {
        return programService.findAllPrograms();
    }

    @ModelAttribute("streams")
    public List<Stream> initializeStreams() {
        return streamService.findAllStreams();
    }
}
