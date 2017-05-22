package com.huntingCube.utility;

import com.huntingCube.dataView.model.ResourceBase;
import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.dataView.model.ResourceHistoryDetails;
import com.huntingCube.login.model.User;
import com.huntingCube.login.model.UserProfile;
import com.huntingCube.login.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;

import java.util.Set;

/**
 * Created by dgup27 on 1/10/2017.
 */
public class HuntingCubeUtility {

    public static void setGlobalModelAttributes(ModelMap model, UserService userService) {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        model.addAttribute("userSSOId", userName);
        User user = userService.findBySSO(userName);
        if (user != null) {
            String displayName = user.getFirstName() + " " + user.getLastName();
            model.addAttribute("userDisplayName", displayName);
            final Set<UserProfile> userProfiles = user.getUserProfiles();
            model.addAttribute("userProfiles", userProfiles);
        }
    }

    public static boolean isNotEmptyOrNull(String objectToCheck) {
        if(objectToCheck != null && !objectToCheck.trim().isEmpty())
            return true;
        else
            return false;
    }

    public static ResourceBase copyResourceData(ResourceBase fromResource, ResourceBase toResource) {

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getName()))
            toResource.setName(fromResource.getName());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getContactNumber())) {
            String contactNo = toResource.getContactNumber();

            if (!HuntingCubeUtility.isNotEmptyOrNull(contactNo)) {
                contactNo = fromResource.getContactNumber();
            }

            if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getContactNumber()) && HuntingCubeUtility.isNotEmptyOrNull(toResource.getContactNumber()) && !fromResource.getContactNumber().replaceAll(" ", "").replaceAll(";", "").equals(toResource.getContactNumber().replaceAll(" ", "").replaceAll(";", ""))) {
                contactNo = contactNo + ";" + fromResource.getContactNumber();
            }
            toResource.setContactNumber(contactNo);
        }

        String emailID = toResource.getEmailId();

        if (!HuntingCubeUtility.isNotEmptyOrNull(emailID)) {
            emailID = fromResource.getEmailId();
        }

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getEmailId()) && HuntingCubeUtility.isNotEmptyOrNull(toResource.getEmailId()) && !fromResource.getEmailId().replaceAll(" ", "").replaceAll(";", "").equals(toResource.getEmailId().replaceAll(" ", "").replaceAll(";", ""))) {
            emailID = emailID + ";" + fromResource.getEmailId();
        }

        toResource.setEmailId(emailID);

        toResource.setInstitute(fromResource.getInstitute());

        toResource.setStream(fromResource.getStream());

        toResource.setProgram(fromResource.getProgram());

        toResource.setPassingYear(fromResource.getPassingYear());

        toResource.setCGPA(fromResource.getCGPA());

        toResource.setAirRank(fromResource.getAirRank());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getOtherRank()))
            toResource.setOtherRank(fromResource.getOtherRank());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getAreaOfExpertise()))
            toResource.setAreaOfExpertise(fromResource.getAreaOfExpertise());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getSkills()))
            toResource.setSkills(fromResource.getSkills());

        toResource.setDesignation(fromResource.getDesignation());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getCompany()))
            toResource.setCompany(fromResource.getCompany());

        toResource.setExperience(fromResource.getExperience());

        toResource.setFixedCTC(fromResource.getFixedCTC());

        toResource.setVariableCTC(fromResource.getVariableCTC());

        toResource.setExpectedCTC(fromResource.getExpectedCTC());

        toResource.setNoticePeriod(fromResource.getNoticePeriod());

        toResource.setCurrentLocation(fromResource.getCurrentLocation());

        toResource.setPreferredLocation(fromResource.getPreferredLocation());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getLinkedinProfile()))
            toResource.setLinkedinProfile(fromResource.getLinkedinProfile());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getFacebookProfile()))
            toResource.setFacebookProfile(fromResource.getFacebookProfile());

        toResource.setAddedDate(fromResource.getAddedDate());
        toResource.setAddedBy(fromResource.getAddedBy());

        return toResource;
    }
}
