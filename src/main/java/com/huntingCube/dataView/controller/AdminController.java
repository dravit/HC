package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dgup27 on 5/20/2017.
 */
@Controller
public class AdminController extends BaseController{

    static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = {"/clientList"}, method = RequestMethod.GET)
    public String clientList(ModelMap model) {

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
            logger.error("Error while saving Client", e);
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

    @RequestMapping(value = {"/clientStatusList"}, method = RequestMethod.GET)
    public String clientStatusList(ModelMap model) {

        List<ClientStatus> clientStatus = clientStatusService.findAllStatus();
        model.addAttribute("clientStatus", clientStatus);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/clientStatusList";
    }

    @RequestMapping(value = {"/addClientStatus"}, method = RequestMethod.POST)
    public String saveClientStatus(@Valid ClientStatus clientStatus, BindingResult result,
                                   ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(clientStatus.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            clientStatusService.save(clientStatus);
        } catch (Exception e) {
            logger.error("Error while saving Client Status", e);
        }
        return "redirect:/clientStatusList";
    }

    @RequestMapping(value = {"/addClientStatus"}, method = RequestMethod.GET)
    public String addClientStatus(ModelMap model) {
        ClientStatus clientStatus = new ClientStatus();
        model.addAttribute("clientStatus", clientStatus);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientStatus";
    }

    @RequestMapping(value = { "/edit-clientStatus-{clientStatusId}" }, method = RequestMethod.GET)
    public String editClientStatus(@PathVariable int clientStatusId, ModelMap model) {
        ClientStatus clientStatus = clientStatusService.findById(clientStatusId);
        model.addAttribute("clientStatus", clientStatus);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientStatus";
    }

    @RequestMapping(value = {"/edit-clientStatus-{clientStatusId}"}, method = RequestMethod.POST)
    public String updateClientStatus(@Valid ClientStatus clientStatus, BindingResult result,
                                     ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addClientStatus";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        clientStatus.setAddedBy((String)model.get("userSSOId"));
        clientStatusService.updateClientStatus(clientStatus);
        return "redirect:/clientStatusList";
    }

    @RequestMapping(value = {"/clientPositionList"}, method = RequestMethod.GET)
    public String clientPositionList(ModelMap model) {

        List<ClientPosition> clientPosition = clientPositionService.findAllPositions();
        model.addAttribute("clientPosition", clientPosition);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/clientPositionList";
    }

    @RequestMapping(value = {"/addClientPosition"}, method = RequestMethod.POST)
    public String saveClientPosition(@Valid ClientPosition clientPosition, BindingResult result,
                                     ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            clientPositionService.save(clientPosition);
        } catch (Exception e) {
            logger.error("Error while saving Client Position", e);
        }
        return "redirect:/clientPositionList";
    }

    @RequestMapping(value = {"/addClientPosition"}, method = RequestMethod.GET)
    public String addClientPosition(ModelMap model) {
        ClientPosition clientPosition = new ClientPosition();
        model.addAttribute("clientPosition", clientPosition);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientPosition";
    }

    @RequestMapping(value = { "/edit-clientPosition-{clientPositionId}" }, method = RequestMethod.GET)
    public String editClientPosition(@PathVariable int clientPositionId, ModelMap model) {
        ClientPosition clientPosition = clientPositionService.findById(clientPositionId);
        model.addAttribute("clientPosition", clientPosition);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientPosition";
    }

    @RequestMapping(value = {"/edit-clientPosition-{clientPositionId}"}, method = RequestMethod.POST)
    public String updateClientPosition(@Valid ClientPosition clientPosition, BindingResult result,
                                       ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addClientPosition";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        clientPosition.setAddedBy((String)model.get("userSSOId"));
        clientPositionService.updateClientPosition(clientPosition);
        return "redirect:/clientPositionList";
    }
}
