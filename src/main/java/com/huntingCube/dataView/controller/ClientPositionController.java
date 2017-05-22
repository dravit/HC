package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.ClientPosition;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by guptado on 22/05/2017.
 */
@Controller
public class ClientPositionController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ClientPositionController.class);

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
            logger.info("clientPosition>>>>>>>>>>>>"+clientPosition);
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
        logger.info("clientPosition>>>>>>>>>>>>>>>"+clientPosition);
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
