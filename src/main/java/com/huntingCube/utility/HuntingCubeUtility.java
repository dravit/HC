package com.huntingCube.utility;

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
}
