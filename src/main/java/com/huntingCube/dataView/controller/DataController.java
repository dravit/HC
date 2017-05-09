package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.*;
import com.huntingCube.dataView.service.*;
import com.huntingCube.login.service.UserService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Controller
@SessionAttributes({"institutes", "locations", "passingYears", "programs", "streams"})
public class DataController {

    static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

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
    ClientService clientService;

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


    /**
     * This method will redirect user having only User role to data view page.
     */
    @RequestMapping(value = {"/", "/dataList"}, method = RequestMethod.GET)
    public String dataList(ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        ResourceDetails resourceDetails = new ResourceDetails();
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("isFilterSet", "false");
        return "dataView/dataList";
    }

    @RequestMapping(value = {"/", "/dataList"}, method = RequestMethod.POST)
    public String fetchDataList(ResourceDetails resourceDetails, ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        List<ResourceDetails> resourceDetailsList = resourceService.findResources(20, resourceDetails);
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("resourceDetailsList", resourceDetailsList);
        return "dataView/dataList";
    }

    @RequestMapping(value = {"/addResourceExcel"}, method = RequestMethod.GET)
    public String uploadExcel(ModelMap model) {
        model.addAttribute("filePath", "");
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/excelUploadUtility";
    }


    @RequestMapping(value = {"/addResource"}, method = RequestMethod.GET)
    public String addResource(ModelMap model) {
        ResourceDetails resourceDetails = new ResourceDetails();
        model.addAttribute("resourceDetails", resourceDetails);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addResource";
    }

    @RequestMapping(value = {"/addResource"}, method = RequestMethod.POST)
    public String saveResource(@Valid ResourceDetails resourceDetails, BindingResult result,
                               ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            resourceDetails.setAddedBy((String) model.get("userSSOId"));
            logger.info(resourceDetails.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            resourceService.save(resourceDetails);
        } catch (Exception e) {
            logger.error("Error while saving resource", e);
        }
        return "redirect:/dataList";
    }

    @RequestMapping(value = {"/resourceDetails-{id}"}, method = RequestMethod.GET)
    public String getResource(@PathVariable String id, ModelMap model) {
        model.addAttribute("resourceDetail", resourceService.findById(Integer.parseInt(id)));
        return "dataView/resourceDetails";
    }

    @RequestMapping(value = {"/addResourceExcel"}, method = RequestMethod.POST)
    public String addDataFromExcel(@Valid String filePath, ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        resourceService.saveExcelRecords(filePath);

        return "dataView/excelUploadUtility";
    }

    @RequestMapping(value = {"/clientHistory-{id}"}, method = RequestMethod.GET)
    public String getClientHistory(@PathVariable String id, ModelMap model) {
        model.addAttribute("resourceDetail", resourceService.findById(Integer.parseInt(id)));
        return "dataView/resourceDetails";
    }

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/clientList"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/clientList";
    }

    @RequestMapping(value = {"/addClient"}, method = RequestMethod.POST)
    public String saveClient(@Valid Client client, BindingResult result,
                             ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(client.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            clientService.save(client);
        } catch (Exception e) {
            logger.error("Error while saving resource", e);
        }
        return "redirect:/clientList";
    }

    @RequestMapping(value = {"/addClient"}, method = RequestMethod.GET)
    public String addClient(ModelMap model) {
        Client client = new Client();
        model.addAttribute("client", client);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClient";
    }

    @RequestMapping(value = { "/edit-client-{clientId}" }, method = RequestMethod.GET)
    public String editClient(@PathVariable int clientId, ModelMap model) {
        Client client = clientService.findById(clientId);
        model.addAttribute("client", client);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClient";
    }

    @RequestMapping(value = {"/edit-client-{clientId}"}, method = RequestMethod.POST)
    public String updateClient(@Valid Client client, BindingResult result,
                             ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addClient";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        client.setAddedBy((String)model.get("userSSOId"));
        clientService.updateClient(client);
        return "redirect:/clientList";
    }
}
